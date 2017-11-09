package dalvinlabs.com.androidlab.nearby;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ActivityNearbyBinding;

public class NearbyActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String LOG_TAG = NearbyActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private GoogleApiClient googleApiClient;
    private static final String NICK_NAME = "Dalvin";
    private static final String SERVICE_ID = "dalvinlabs";

    public class ViewModel {
        public final ObservableField<String> discover = new ObservableField<>("Discover nearby devices");
        public final ObservableField<String> found = new ObservableField<>();
        public final ObservableField<String> initiated = new ObservableField<>();
        public final ObservableField<String> connected = new ObservableField<>();
        public final ObservableField<String> disconnected = new ObservableField<>();
        public final ObservableField<String> endPointConnected = new ObservableField<>("");
        public final ObservableField<String> data = new ObservableField<>("");
        public final ObservableBoolean isConnected = new ObservableBoolean(false);
    }

    private ViewModel viewModel = new ViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNearbyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_nearby);
        binding.setViewModel(viewModel);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(com.google.android.gms.nearby.Nearby.CONNECTIONS_API)
                .build();
    }

    private void checkPermissionsAndStartDiscovery() {
        Log.d(LOG_TAG, "checkPermissionsAndStartDiscovery");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            startDiscovery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.d(LOG_TAG, "onRequestPermissionsResult");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(LOG_TAG, "Location permission granted");
                    startDiscovery();
                } else {
                    Log.d(LOG_TAG, "Location permission declined");
                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(LOG_TAG,"googleApiClient onConnected");
        checkPermissionsAndStartDiscovery();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(LOG_TAG,"googleApiClient onConnectionSuspended i = " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(LOG_TAG,"googleApiClient onConnectionFailed connectionResult = " + connectionResult.getErrorMessage());
    }

    @Override
    public void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    /*
        Invokes on discovery
     */
    private EndpointDiscoveryCallback endpointDiscoveryCallback = new EndpointDiscoveryCallback() {
        @Override
        public void onEndpointFound(String endPoint, DiscoveredEndpointInfo discoveredEndpointInfo) {
            Log.d(LOG_TAG, "onEndpointFound, endPoint = " + endPoint + " , discoveredEndpointInfo = " + discoveredEndpointInfo.toString());
            viewModel.found.set("Found = " + discoveredEndpointInfo.getEndpointName() + " , ServiceId : " + discoveredEndpointInfo.getServiceId());
            // Request connection
            Nearby.Connections.requestConnection(googleApiClient, NICK_NAME, endPoint, connectionLifecycleCallback)
                    .setResultCallback(status -> Log.d(LOG_TAG,"requestConnection status = " + status.toString()));
        }

        @Override
        public void onEndpointLost(String endPoint) {
            Log.d(LOG_TAG, "onEndpointLost = " + endPoint);
        }
    };

    /*
        Invokes if connection is successful or not
     */
    private ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() {
        @Override
        public void onConnectionInitiated(String endPoint, ConnectionInfo connectionInfo) {
            Log.d(LOG_TAG, "onConnectionInitiated, endPoint = " + endPoint + " , connectionInfo = " + connectionInfo.toString());
            // Accept connection
            com.google.android.gms.nearby.Nearby.Connections.acceptConnection(googleApiClient, endPoint, payloadCallback);
            viewModel.initiated.set("Initiated = " + connectionInfo.getEndpointName() + " , token : " + connectionInfo.getAuthenticationToken());
        }

        @Override
        public void onConnectionResult(String endPoint, ConnectionResolution connectionResolution) {
            Log.d(LOG_TAG, "onConnectionResult, endPoint = " + endPoint + " , connectionResolution = " + connectionResolution.toString());
            viewModel.connected.set("Connected, endPoint = " + endPoint);
            viewModel.endPointConnected.set(endPoint);
            viewModel.isConnected.set(true);
        }

        @Override
        public void onDisconnected(String endPoint) {
            Log.d(LOG_TAG, "onDisconnected, endPoint = " + endPoint);
            viewModel.disconnected.set("Disconnected, endPoint = " + endPoint);
            viewModel.endPointConnected.set("");
            viewModel.isConnected.set(false);
        }
    };

    /*
        Invokes to exchange data
     */
    private PayloadCallback payloadCallback = new PayloadCallback() {
        @Override
        public void onPayloadReceived(String endPoint, Payload payload) {
            String data = "";
            byte[] bytes;
            if ((bytes = payload.asBytes()) != null) {
                data = new String(bytes);
            }
            Log.d(LOG_TAG, "onPayloadReceived endPoint = " + endPoint +
                    " , payload = " + data);
        }

        @Override
        public void onPayloadTransferUpdate(String s, PayloadTransferUpdate payloadTransferUpdate) {

        }
    };

    private void startDiscovery() {
        Nearby.Connections.startDiscovery(googleApiClient, SERVICE_ID,
                endpointDiscoveryCallback, new DiscoveryOptions(Strategy.P2P_STAR))
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Log.d(LOG_TAG, "startDiscovery status = " + status.toString());
                    }
                });
    }

    public void onRefresh(View view) {
        Log.d(LOG_TAG, "onRefresh");
        viewModel.found.set("");
        viewModel.initiated.set("");
        viewModel.connected.set("");
        viewModel.disconnected.set("");
        Nearby.Connections.stopDiscovery(googleApiClient);
        startDiscovery();
    }

    public void sendData(View view) {
        Log.d(LOG_TAG, "sendData, data = " + viewModel.data.get());
        Nearby.Connections.sendPayload(googleApiClient, viewModel.endPointConnected.get(), Payload.fromBytes(viewModel.data.get().getBytes()))
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Log.d(LOG_TAG,"sendData onResult status = " + status.toString());
                    }
                });
    }

    public void disconnect(View view) {
        Nearby.Connections.stopDiscovery(googleApiClient);
        if (viewModel.endPointConnected.get() != null && !viewModel.endPointConnected.get().isEmpty()) {
            Nearby.Connections.disconnectFromEndpoint(googleApiClient, viewModel.endPointConnected.get());
        }
    }

    @Override
    protected void onDestroy() {
        disconnect(null);
        googleApiClient.disconnect();
        super.onDestroy();
    }
}
