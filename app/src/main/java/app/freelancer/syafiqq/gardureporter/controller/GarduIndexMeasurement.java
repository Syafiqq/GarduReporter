package app.freelancer.syafiqq.gardureporter.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.controller.adapter.GarduIndexAdapter;
import app.freelancer.syafiqq.gardureporter.model.custom.android.location.BooleanObserver;
import app.freelancer.syafiqq.gardureporter.model.dao.GarduDao;
import app.freelancer.syafiqq.gardureporter.model.dao.TokenDao;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduIndexMeasurementOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduIndexOrm;
import app.freelancer.syafiqq.gardureporter.model.util.GPSApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.riyagayasen.easyaccordion.AccordionView;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

public class GarduIndexMeasurement extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, Observer
{
    public static final int REQUEST_COUNT_LIMIT = 5;
    public static final int REQUEST_TIME_LIMIT = 30;


    private GarduIndexMeasurementOrm measurement;

    private SearchableSpinner noGardu;

    private AccordionView jurusanUmum;
    private AccordionView jurusanUmum1;
    private AccordionView jurusanUmum2;
    private AccordionView jurusanUmum3;
    private AccordionView jurusanUmum4;
    private AccordionView jurusanKhusus;
    private AccordionView jurusanKhusus1;
    private AccordionView jurusanKhusus2;
    private TextInputEditText arusR;
    private TextInputEditText arusS;
    private TextInputEditText arusT;
    private TextInputEditText arusN;
    private TextInputEditText teganganRN;
    private TextInputEditText teganganSN;
    private TextInputEditText teganganTN;
    private TextInputEditText teganganRS;
    private TextInputEditText teganganRT;
    private TextInputEditText teganganST;
    private TextInputEditText jurusanUmum1ID;
    private TextInputEditText jurusanUmum1ArusR;
    private TextInputEditText jurusanUmum1ArusS;
    private TextInputEditText jurusanUmum1ArusT;
    private TextInputEditText jurusanUmum1ArusN;
    private TextInputEditText jurusanUmum1TeganganRN;
    private TextInputEditText jurusanUmum1TeganganSN;
    private TextInputEditText jurusanUmum1TeganganTN;
    private TextInputEditText jurusanUmum1TeganganRS;
    private TextInputEditText jurusanUmum1TeganganRT;
    private TextInputEditText jurusanUmum1TeganganST;
    private TextInputEditText jurusanUmum2ID;
    private TextInputEditText jurusanUmum2ArusR;
    private TextInputEditText jurusanUmum2ArusS;
    private TextInputEditText jurusanUmum2ArusT;
    private TextInputEditText jurusanUmum2ArusN;
    private TextInputEditText jurusanUmum2TeganganRN;
    private TextInputEditText jurusanUmum2TeganganSN;
    private TextInputEditText jurusanUmum2TeganganTN;
    private TextInputEditText jurusanUmum2TeganganRS;
    private TextInputEditText jurusanUmum2TeganganRT;
    private TextInputEditText jurusanUmum2TeganganST;
    private TextInputEditText jurusanUmum3ID;
    private TextInputEditText jurusanUmum3ArusR;
    private TextInputEditText jurusanUmum3ArusS;
    private TextInputEditText jurusanUmum3ArusT;
    private TextInputEditText jurusanUmum3ArusN;
    private TextInputEditText jurusanUmum3TeganganRN;
    private TextInputEditText jurusanUmum3TeganganSN;
    private TextInputEditText jurusanUmum3TeganganTN;
    private TextInputEditText jurusanUmum3TeganganRS;
    private TextInputEditText jurusanUmum3TeganganRT;
    private TextInputEditText jurusanUmum3TeganganST;
    private TextInputEditText jurusanUmum4ID;
    private TextInputEditText jurusanUmum4ArusR;
    private TextInputEditText jurusanUmum4ArusS;
    private TextInputEditText jurusanUmum4ArusT;
    private TextInputEditText jurusanUmum4ArusN;
    private TextInputEditText jurusanUmum4TeganganRN;
    private TextInputEditText jurusanUmum4TeganganSN;
    private TextInputEditText jurusanUmum4TeganganTN;
    private TextInputEditText jurusanUmum4TeganganRS;
    private TextInputEditText jurusanUmum4TeganganRT;
    private TextInputEditText jurusanUmum4TeganganST;
    private TextInputEditText jurusanKhusus1ID;
    private TextInputEditText jurusanKhusus1ArusR;
    private TextInputEditText jurusanKhusus1ArusS;
    private TextInputEditText jurusanKhusus1ArusT;
    private TextInputEditText jurusanKhusus1ArusN;
    private TextInputEditText jurusanKhusus1TeganganRN;
    private TextInputEditText jurusanKhusus1TeganganSN;
    private TextInputEditText jurusanKhusus1TeganganTN;
    private TextInputEditText jurusanKhusus1TeganganRS;
    private TextInputEditText jurusanKhusus1TeganganRT;
    private TextInputEditText jurusanKhusus1TeganganST;
    private TextInputEditText jurusanKhusus2ID;
    private TextInputEditText jurusanKhusus2ArusR;
    private TextInputEditText jurusanKhusus2ArusS;
    private TextInputEditText jurusanKhusus2ArusT;
    private TextInputEditText jurusanKhusus2ArusN;
    private TextInputEditText jurusanKhusus2TeganganRN;
    private TextInputEditText jurusanKhusus2TeganganSN;
    private TextInputEditText jurusanKhusus2TeganganTN;
    private TextInputEditText jurusanKhusus2TeganganRS;
    private TextInputEditText jurusanKhusus2TeganganRT;
    private TextInputEditText jurusanKhusus2TeganganST;

    private Observer serviceObserver;
    private Location location;
    private FloatingActionButton submit;
    private ProgressBar progress;
    private Switch gpsSwitchReqeust;

    private GoogleMap map;
    private Marker marker;
    private LinearLayout mapContainer;
    private GPSApi gpsApi;

    private int countRequest;
    private CountDownLatch countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timber.d("onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardu_index_measurement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.submit = (FloatingActionButton) super.findViewById(R.id.content_gardu_index_measurement_floatingactionbutton_submit);
        this.submit.setOnClickListener(this);
        this.gpsApi = new GPSApi(this);

        if(!this.gpsApi.checkPermissions())
        {
            this.gpsApi.requestPermissions(R.id.activity_dashboard_root);
        }
        this.gpsApi.initializeService();
    }

    @Override protected void onStart()
    {
        Timber.d("onStart");

        super.onStart();

        this.progress = (ProgressBar) findViewById(R.id.content_gardu_index_measurement_progress_bar_submit);
        this.noGardu = (SearchableSpinner) findViewById(R.id.content_gardu_index_measurement_searchablespinner_no_gardu);
        final ImageButton garduIndexSync = (ImageButton) findViewById(R.id.content_gardu_index_measurement_imagebutton_no_gardu_refresh);
        final ArrayAdapter<GarduIndexOrm> garduIndexAdapter = new GarduIndexAdapter(super.getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<GarduIndexOrm>());

        this.jurusanUmum = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_umum);
        this.jurusanUmum1 = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_umum_1);
        this.jurusanUmum2 = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_umum_2);
        this.jurusanUmum3 = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_umum_3);
        this.jurusanUmum4 = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_umum_4);
        this.jurusanKhusus = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_khusus);
        this.jurusanKhusus1 = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_khusus_1);
        this.jurusanKhusus2 = (AccordionView) findViewById(R.id.content_gardu_index_measurement_accordionview_jurusan_khusus_2);

        this.arusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_arus_r);
        this.arusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_arus_s);
        this.arusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_arus_t);
        this.arusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_arus_n);
        this.teganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_tegangan_rn);
        this.teganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_tegangan_sn);
        this.teganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_tegangan_tn);
        this.teganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_tegangan_rs);
        this.teganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_tegangan_rt);
        this.teganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_tegangan_st);

        this.jurusanUmum1ID = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_id_jurusan);
        this.jurusanUmum1ArusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_arus_r);
        this.jurusanUmum1ArusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_arus_s);
        this.jurusanUmum1ArusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_arus_t);
        this.jurusanUmum1ArusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_arus_n);
        this.jurusanUmum1TeganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_tegangan_rn);
        this.jurusanUmum1TeganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_tegangan_sn);
        this.jurusanUmum1TeganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_tegangan_tn);
        this.jurusanUmum1TeganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_tegangan_rs);
        this.jurusanUmum1TeganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_tegangan_rt);
        this.jurusanUmum1TeganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_1_tegangan_st);

        this.jurusanUmum2ID = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_id_jurusan);
        this.jurusanUmum2ArusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_arus_r);
        this.jurusanUmum2ArusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_arus_s);
        this.jurusanUmum2ArusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_arus_t);
        this.jurusanUmum2ArusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_arus_n);
        this.jurusanUmum2TeganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_tegangan_rn);
        this.jurusanUmum2TeganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_tegangan_sn);
        this.jurusanUmum2TeganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_tegangan_tn);
        this.jurusanUmum2TeganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_tegangan_rs);
        this.jurusanUmum2TeganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_tegangan_rt);
        this.jurusanUmum2TeganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_2_tegangan_st);

        this.jurusanUmum3ID = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_id_jurusan);
        this.jurusanUmum3ArusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_arus_r);
        this.jurusanUmum3ArusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_arus_s);
        this.jurusanUmum3ArusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_arus_t);
        this.jurusanUmum3ArusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_arus_n);
        this.jurusanUmum3TeganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_tegangan_rn);
        this.jurusanUmum3TeganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_tegangan_sn);
        this.jurusanUmum3TeganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_tegangan_tn);
        this.jurusanUmum3TeganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_tegangan_rs);
        this.jurusanUmum3TeganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_tegangan_rt);
        this.jurusanUmum3TeganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_3_tegangan_st);

        this.jurusanUmum4ID = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_id_jurusan);
        this.jurusanUmum4ArusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_arus_r);
        this.jurusanUmum4ArusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_arus_s);
        this.jurusanUmum4ArusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_arus_t);
        this.jurusanUmum4ArusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_arus_n);
        this.jurusanUmum4TeganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_tegangan_rn);
        this.jurusanUmum4TeganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_tegangan_sn);
        this.jurusanUmum4TeganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_tegangan_tn);
        this.jurusanUmum4TeganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_tegangan_rs);
        this.jurusanUmum4TeganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_tegangan_rt);
        this.jurusanUmum4TeganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_umum_4_tegangan_st);

        this.jurusanKhusus1ID = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_id_jurusan);
        this.jurusanKhusus1ArusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_arus_r);
        this.jurusanKhusus1ArusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_arus_s);
        this.jurusanKhusus1ArusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_arus_t);
        this.jurusanKhusus1ArusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_arus_n);
        this.jurusanKhusus1TeganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_tegangan_rn);
        this.jurusanKhusus1TeganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_tegangan_sn);
        this.jurusanKhusus1TeganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_tegangan_tn);
        this.jurusanKhusus1TeganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_tegangan_rs);
        this.jurusanKhusus1TeganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_tegangan_rt);
        this.jurusanKhusus1TeganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_1_tegangan_st);

        this.jurusanKhusus2ID = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_id_jurusan);
        this.jurusanKhusus2ArusR = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_arus_r);
        this.jurusanKhusus2ArusS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_arus_s);
        this.jurusanKhusus2ArusT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_arus_t);
        this.jurusanKhusus2ArusN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_arus_n);
        this.jurusanKhusus2TeganganRN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_tegangan_rn);
        this.jurusanKhusus2TeganganSN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_tegangan_sn);
        this.jurusanKhusus2TeganganTN = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_tegangan_tn);
        this.jurusanKhusus2TeganganRS = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_tegangan_rs);
        this.jurusanKhusus2TeganganRT = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_tegangan_rt);
        this.jurusanKhusus2TeganganST = (TextInputEditText) findViewById(R.id.content_gardu_index_measurement_jurusan_khusus_2_tegangan_st);

        this.mapContainer = (LinearLayout) findViewById(R.id.content_gardu_index_measurement_linearlayout_map_container);
        this.gpsSwitchReqeust = (Switch) findViewById(R.id.content_gardu_index_measurement_switch_location);
        final SupportMapFragment mapFragment = (SupportMapFragment) super.getSupportFragmentManager().findFragmentById(R.id.content_gardu_index_measurement_fragment_map);

        this.measurement = new GarduIndexMeasurementOrm()
        {
            @Override public void reset()
            {
                super.reset();
                GarduIndexMeasurement.this.resetForm();
            }
        };

        garduIndexSync.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                GarduDao.findAllIndexB231A(GarduIndexMeasurement.super.getApplicationContext(), new GarduDao.GarduResponseListener<List<GarduIndexOrm>>()
                {
                    @Override public void onResponseFailed(int status, String message)
                    {
                        Timber.d("onResponseFailed");

                        if(message != null)
                        {
                            Toast.makeText(GarduIndexMeasurement.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onResponseSuccessful(List<GarduIndexOrm> gardu, int status, String message)
                    {
                        Timber.d("onResponseSuccessful");

                        ((GarduIndexAdapter) garduIndexAdapter).update(gardu);
                        garduIndexAdapter.notifyDataSetChanged();
                        this.onResponseFailed(status, message);
                    }
                });
            }
        });

        this.serviceObserver = new Observer()
        {
            @Override public void update(Observable observable, Object o)
            {
                BooleanObserver bool = (BooleanObserver) observable;
                final boolean resultedPermission = GarduIndexMeasurement.this.gpsApi.checkPermissions() && bool.isBool();
                GarduIndexMeasurement.this.shiftUISubmit(resultedPermission);
            }
        };

        this.gpsSwitchReqeust.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                if(checked)
                {
                    GarduIndexMeasurement.this.onGPSSwitchedOn();
                }
                else
                {
                    GarduIndexMeasurement.this.onGPSSwitchedOff();
                }
            }
        });

        this.noGardu.setAdapter(garduIndexAdapter);
        this.noGardu.setTitle("Select Gardu");
        this.noGardu.setPositiveButton("OK");

        this.gpsApi.addObserver(this);
        this.gpsApi.getAvailability().addObserver(this.serviceObserver);

        this.shiftUISubmit(true);
        this.gpsSwitchReqeust.setChecked(false);
        garduIndexSync.callOnClick();
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Timber.d("onCreateOptionsMenu");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_gardu_index_measurement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Timber.d("onOptionsItemSelected");

        // Handle item selection
        switch(item.getItemId())
        {
            case R.id.activity_dashboard_menu_logout:
            {
                TokenDao.logoutAccount(this);
                return true;
            }
            case R.id.activity_dashboard_menu_jump_to_dashboard:
            {
                @NotNull Intent intent = new Intent(GarduIndexMeasurement.this, GarduIndexInsert.class);
                super.startActivity(intent);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Timber.d("onActivityResult");

        switch(requestCode)
        {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case GPSApi.REQUEST_CHECK_SETTINGS:
            {
                switch(resultCode)
                {
                    case Activity.RESULT_OK:
                    {
                        Timber.d("User agreed to make required localtion settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                    }
                    break;
                    case Activity.RESULT_CANCELED:
                    {
                        Timber.d("User chose not to make required localtion settings changes.");
                    }
                    break;
                }
                this.gpsSwitchReqeust.setChecked(false);
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults)
    {
        Timber.d("onRequestPermissionsResult");

        if(requestCode == GPSApi.REQUEST_PERMISSIONS_REQUEST_CODE)
        {
            if(grantResults.length <= 0)
            {
                Timber.d("Permission Denied");
            }
            else
            {
                Timber.d("Permission Granted");

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    this.serviceObserver.update(this.gpsApi.getAvailability(), false);
                }
                else
                {
                    Timber.d("Permission Refused");

                    Snackbar.make(
                            findViewById(R.id.activity_dashboard_root),
                            R.string.permission_denied_explanation,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.command_setting, new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    // Build intent that displays the App settings screen.
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                                    intent.setData(uri);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
            }
        }
    }

    @Override public void onClick(View view)
    {
        Timber.d("onClick");

        this.measurement.setNoGardu(((GarduIndexOrm) this.noGardu.getSelectedItem()).getNo());
        this.measurement.setPetugas1(null);
        this.measurement.setPetugas2(null);
        this.measurement.setNoKontrak(null);

        this.measurement.setArusRUtama(this.getStringOrDefault(this.arusR.getText().toString(), null));
        this.measurement.setArusSUtama(this.getStringOrDefault(this.arusS.getText().toString(), null));
        this.measurement.setArusTUtama(this.getStringOrDefault(this.arusT.getText().toString(), null));
        this.measurement.setArusNUtama(this.getStringOrDefault(this.arusN.getText().toString(), null));
        this.measurement.setTeganganRNUtama(this.getStringOrDefault(this.teganganRN.getText().toString(), null));
        this.measurement.setTeganganSNUtama(this.getStringOrDefault(this.teganganSN.getText().toString(), null));
        this.measurement.setTeganganTNUtama(this.getStringOrDefault(this.teganganTN.getText().toString(), null));
        this.measurement.setTeganganRSUtama(this.getStringOrDefault(this.teganganRS.getText().toString(), null));
        this.measurement.setTeganganRTUtama(this.getStringOrDefault(this.teganganRT.getText().toString(), null));
        this.measurement.setTeganganSTUtama(this.getStringOrDefault(this.teganganST.getText().toString(), null));

        if(this.jurusanUmum.isExpanded())
        {
            if(this.jurusanUmum1.isExpanded())
            {
                this.measurement.setIdJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1ID.getText().toString(), null));
                this.measurement.setArusRJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1ArusR.getText().toString(), null));
                this.measurement.setArusSJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1ArusS.getText().toString(), null));
                this.measurement.setArusTJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1ArusT.getText().toString(), null));
                this.measurement.setArusNJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1ArusN.getText().toString(), null));
                this.measurement.setTeganganRNJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1TeganganRN.getText().toString(), null));
                this.measurement.setTeganganSNJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1TeganganSN.getText().toString(), null));
                this.measurement.setTeganganTNJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1TeganganTN.getText().toString(), null));
                this.measurement.setTeganganRSJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1TeganganRS.getText().toString(), null));
                this.measurement.setTeganganRTJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1TeganganRT.getText().toString(), null));
                this.measurement.setTeganganSTJurusanUmum1(this.getStringOrDefault(this.jurusanUmum1TeganganST.getText().toString(), null));
            }

            if(this.jurusanUmum2.isExpanded())
            {
                this.measurement.setIdJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2ID.getText().toString(), null));
                this.measurement.setArusRJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2ArusR.getText().toString(), null));
                this.measurement.setArusSJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2ArusS.getText().toString(), null));
                this.measurement.setArusTJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2ArusT.getText().toString(), null));
                this.measurement.setArusNJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2ArusN.getText().toString(), null));
                this.measurement.setTeganganRNJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2TeganganRN.getText().toString(), null));
                this.measurement.setTeganganSNJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2TeganganSN.getText().toString(), null));
                this.measurement.setTeganganTNJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2TeganganTN.getText().toString(), null));
                this.measurement.setTeganganRSJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2TeganganRS.getText().toString(), null));
                this.measurement.setTeganganRTJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2TeganganRT.getText().toString(), null));
                this.measurement.setTeganganSTJurusanUmum2(this.getStringOrDefault(this.jurusanUmum2TeganganST.getText().toString(), null));
            }

            if(this.jurusanUmum3.isExpanded())
            {
                this.measurement.setIdJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3ID.getText().toString(), null));
                this.measurement.setArusRJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3ArusR.getText().toString(), null));
                this.measurement.setArusSJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3ArusS.getText().toString(), null));
                this.measurement.setArusTJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3ArusT.getText().toString(), null));
                this.measurement.setArusNJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3ArusN.getText().toString(), null));
                this.measurement.setTeganganRNJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3TeganganRN.getText().toString(), null));
                this.measurement.setTeganganSNJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3TeganganSN.getText().toString(), null));
                this.measurement.setTeganganTNJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3TeganganTN.getText().toString(), null));
                this.measurement.setTeganganRSJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3TeganganRS.getText().toString(), null));
                this.measurement.setTeganganRTJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3TeganganRT.getText().toString(), null));
                this.measurement.setTeganganSTJurusanUmum3(this.getStringOrDefault(this.jurusanUmum3TeganganST.getText().toString(), null));
            }

            if(this.jurusanUmum4.isExpanded())
            {
                this.measurement.setIdJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4ID.getText().toString(), null));
                this.measurement.setArusRJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4ArusR.getText().toString(), null));
                this.measurement.setArusSJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4ArusS.getText().toString(), null));
                this.measurement.setArusTJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4ArusT.getText().toString(), null));
                this.measurement.setArusNJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4ArusN.getText().toString(), null));
                this.measurement.setTeganganRNJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4TeganganRN.getText().toString(), null));
                this.measurement.setTeganganSNJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4TeganganSN.getText().toString(), null));
                this.measurement.setTeganganTNJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4TeganganTN.getText().toString(), null));
                this.measurement.setTeganganRSJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4TeganganRS.getText().toString(), null));
                this.measurement.setTeganganRTJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4TeganganRT.getText().toString(), null));
                this.measurement.setTeganganSTJurusanUmum4(this.getStringOrDefault(this.jurusanUmum4TeganganST.getText().toString(), null));
            }
        }

        if(this.jurusanKhusus.isExpanded())
        {
            if(this.jurusanKhusus1.isExpanded())
            {
                this.measurement.setIdJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1ID.getText().toString(), null));
                this.measurement.setArusRJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1ArusR.getText().toString(), null));
                this.measurement.setArusSJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1ArusS.getText().toString(), null));
                this.measurement.setArusTJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1ArusT.getText().toString(), null));
                this.measurement.setArusNJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1ArusN.getText().toString(), null));
                this.measurement.setTeganganRNJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1TeganganRN.getText().toString(), null));
                this.measurement.setTeganganSNJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1TeganganSN.getText().toString(), null));
                this.measurement.setTeganganTNJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1TeganganTN.getText().toString(), null));
                this.measurement.setTeganganRSJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1TeganganRS.getText().toString(), null));
                this.measurement.setTeganganRTJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1TeganganRT.getText().toString(), null));
                this.measurement.setTeganganSTJurusanKhusus1(this.getStringOrDefault(this.jurusanKhusus1TeganganST.getText().toString(), null));
            }

            if(this.jurusanKhusus2.isExpanded())
            {
                this.measurement.setIdJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2ID.getText().toString(), null));
                this.measurement.setArusRJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2ArusR.getText().toString(), null));
                this.measurement.setArusSJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2ArusS.getText().toString(), null));
                this.measurement.setArusTJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2ArusT.getText().toString(), null));
                this.measurement.setArusNJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2ArusN.getText().toString(), null));
                this.measurement.setTeganganRNJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2TeganganRN.getText().toString(), null));
                this.measurement.setTeganganSNJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2TeganganSN.getText().toString(), null));
                this.measurement.setTeganganTNJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2TeganganTN.getText().toString(), null));
                this.measurement.setTeganganRSJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2TeganganRS.getText().toString(), null));
                this.measurement.setTeganganRTJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2TeganganRT.getText().toString(), null));
                this.measurement.setTeganganSTJurusanKhusus2(this.getStringOrDefault(this.jurusanKhusus2TeganganST.getText().toString(), null));
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Data yang anda kirim adalah benar?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                GarduIndexMeasurement.this.onSubmitPreparation();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        builder.show();
    }

    @Override public void onMapReady(GoogleMap googleMap)
    {
        Timber.d("onMapReady");

        googleMap.setMinZoomPreference(15.0f);
        googleMap.setMaxZoomPreference(18.0f);
        this.map = googleMap;
    }

    @Override public void update(Observable observable, @Nullable Object o)
    {
        Timber.d("update");

        ++this.countRequest;
        if(this.countDown != null)
        {
            this.countDown.countDown();
        }

        @Nullable final Location location = (Location) o;
        if(location != null)
        {
            Timber.d("Location [%b] [%.14g,%.14g] [%s]", true, location.getLatitude(), location.getLongitude(), this.countRequest);

            this.location = location;
        }
        else
        {
            Timber.d("Location [%b] [%s]", false, this.countRequest);
        }

        this.updateMap(this.map, location);
    }

    @Override protected void onResume()
    {
        Timber.d("onResume");

        this.serviceObserver.update(this.gpsApi.getAvailability(), false);

        super.onResume();
    }

    @Override protected void onStop()
    {
        Timber.d("onStop");

        this.gpsApi.deleteObserver(this);
        this.gpsApi.getAvailability().deleteObserver(this.serviceObserver);

        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        Timber.d("onDestroy");

        this.gpsApi.destroyService();
        super.onDestroy();
    }
    //==============================================================================================

    private void onGPSSwitchedOff()
    {
        Timber.d("onGPSSwitchedOff");

        this.shiftUISubmit(true);
        this.gpsApi.removeLocationUpdates();
        this.location = null;
        this.mapContainer.setVisibility(View.GONE);
    }

    private void onGPSSwitchedOn()
    {
        Timber.d("onGPSSwitchedOn");

        if(!this.gpsApi.checkPermissions())
        {
            this.gpsApi.requestPermissions(R.id.activity_dashboard_root);
        }
        else
        {
            if(!this.gpsApi.isAlreadyRequested())
            {
                this.countRequest = 0;
                this.location = null;
                this.gpsApi.requestLocationUpdates();
            }
        }
        this.mapContainer.setVisibility(View.VISIBLE);
    }

    private void resetForm()
    {
        Timber.d("resetForm");

        this.arusR.setText("");
        this.arusS.setText("");
        this.arusT.setText("");
        this.arusN.setText("");
        this.teganganRN.setText("");
        this.teganganSN.setText("");
        this.teganganTN.setText("");
        this.teganganRS.setText("");
        this.teganganRT.setText("");
        this.teganganST.setText("");
        this.jurusanUmum1ID.setText("");
        this.jurusanUmum1ArusR.setText("");
        this.jurusanUmum1ArusS.setText("");
        this.jurusanUmum1ArusT.setText("");
        this.jurusanUmum1ArusN.setText("");
        this.jurusanUmum1TeganganRN.setText("");
        this.jurusanUmum1TeganganSN.setText("");
        this.jurusanUmum1TeganganTN.setText("");
        this.jurusanUmum1TeganganRS.setText("");
        this.jurusanUmum1TeganganRT.setText("");
        this.jurusanUmum1TeganganST.setText("");
        this.jurusanUmum2ID.setText("");
        this.jurusanUmum2ArusR.setText("");
        this.jurusanUmum2ArusS.setText("");
        this.jurusanUmum2ArusT.setText("");
        this.jurusanUmum2ArusN.setText("");
        this.jurusanUmum2TeganganRN.setText("");
        this.jurusanUmum2TeganganSN.setText("");
        this.jurusanUmum2TeganganTN.setText("");
        this.jurusanUmum2TeganganRS.setText("");
        this.jurusanUmum2TeganganRT.setText("");
        this.jurusanUmum2TeganganST.setText("");
        this.jurusanUmum3ID.setText("");
        this.jurusanUmum3ArusR.setText("");
        this.jurusanUmum3ArusS.setText("");
        this.jurusanUmum3ArusT.setText("");
        this.jurusanUmum3ArusN.setText("");
        this.jurusanUmum3TeganganRN.setText("");
        this.jurusanUmum3TeganganSN.setText("");
        this.jurusanUmum3TeganganTN.setText("");
        this.jurusanUmum3TeganganRS.setText("");
        this.jurusanUmum3TeganganRT.setText("");
        this.jurusanUmum3TeganganST.setText("");
        this.jurusanUmum4ID.setText("");
        this.jurusanUmum4ArusR.setText("");
        this.jurusanUmum4ArusS.setText("");
        this.jurusanUmum4ArusT.setText("");
        this.jurusanUmum4ArusN.setText("");
        this.jurusanUmum4TeganganRN.setText("");
        this.jurusanUmum4TeganganSN.setText("");
        this.jurusanUmum4TeganganTN.setText("");
        this.jurusanUmum4TeganganRS.setText("");
        this.jurusanUmum4TeganganRT.setText("");
        this.jurusanUmum4TeganganST.setText("");
        this.jurusanKhusus1ID.setText("");
        this.jurusanKhusus1ArusR.setText("");
        this.jurusanKhusus1ArusS.setText("");
        this.jurusanKhusus1ArusT.setText("");
        this.jurusanKhusus1ArusN.setText("");
        this.jurusanKhusus1TeganganRN.setText("");
        this.jurusanKhusus1TeganganSN.setText("");
        this.jurusanKhusus1TeganganTN.setText("");
        this.jurusanKhusus1TeganganRS.setText("");
        this.jurusanKhusus1TeganganRT.setText("");
        this.jurusanKhusus1TeganganST.setText("");
        this.jurusanKhusus2ID.setText("");
        this.jurusanKhusus2ArusR.setText("");
        this.jurusanKhusus2ArusS.setText("");
        this.jurusanKhusus2ArusT.setText("");
        this.jurusanKhusus2ArusN.setText("");
        this.jurusanKhusus2TeganganRN.setText("");
        this.jurusanKhusus2TeganganSN.setText("");
        this.jurusanKhusus2TeganganTN.setText("");
        this.jurusanKhusus2TeganganRS.setText("");
        this.jurusanKhusus2TeganganRT.setText("");
        this.jurusanKhusus2TeganganST.setText("");

        this.jurusanKhusus2.setExpanded(false);
        this.jurusanKhusus1.setExpanded(false);
        this.jurusanKhusus.setExpanded(false);
        this.jurusanUmum4.setExpanded(false);
        this.jurusanUmum3.setExpanded(false);
        this.jurusanUmum2.setExpanded(false);
        this.jurusanUmum1.setExpanded(false);
        this.jurusanUmum.setExpanded(false);
    }

    public String getStringOrDefault(String val, String replacement)
    {
        Timber.d("getStringOrDefault");

        return TextUtils.isEmpty(val) ? replacement : val;
    }

    private void onSubmitPreparation()
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override protected void onPreExecute()
            {
                super.onPreExecute();

                int remaining = GarduIndexMeasurement.REQUEST_COUNT_LIMIT - GarduIndexMeasurement.this.countRequest;
                if(remaining > 0)
                {
                    GarduIndexMeasurement.this.countDown = new CountDownLatch(remaining > 0 ? remaining : 0);
                    GarduIndexMeasurement.this.gpsSwitchReqeust.setChecked(true);
                    GarduIndexMeasurement.this.shiftUISubmit(false);
                }
            }

            @Override protected Void doInBackground(Void... voids)
            {
                try
                {
                    if(GarduIndexMeasurement.this.countDown != null)
                    {
                        GarduIndexMeasurement.this.countDown.await(GarduIndexMeasurement.REQUEST_TIME_LIMIT, TimeUnit.SECONDS);
                    }
                }
                catch(InterruptedException e)
                {
                    Timber.e(e);
                }
                return null;
            }

            @Override protected void onPostExecute(Void aVoid)
            {
                if(GarduIndexMeasurement.this.location == null)
                {
                    Toast.makeText(GarduIndexMeasurement.this, GarduIndexMeasurement.super.getResources().getString(R.string.error_no_location_retrieved), Toast.LENGTH_SHORT).show();
                    GarduIndexMeasurement.this.shiftUISubmit(true);
                }
                else
                {
                    GarduIndexMeasurement.this.measurement.setLatitude(GarduIndexMeasurement.this.location.getLatitude());
                    GarduIndexMeasurement.this.measurement.setLongitude(GarduIndexMeasurement.this.location.getLongitude());
                    GarduIndexMeasurement.this.onSubmitConfirmed(GarduIndexMeasurement.this.measurement);
                }
            }
        }.execute();
    }

    private void onSubmitConfirmed(final GarduIndexMeasurementOrm measurement)
    {
        Timber.d("onSubmitConfirmed");

        GarduDao.sendGarduMeasurement(super.getApplicationContext(), measurement, new GarduDao.GarduRequestListener()
        {
            @Override public void onRequestFailed(int status, String message)
            {
                Timber.d("onRequestFailed");

                if(message != null)
                {
                    Toast.makeText(GarduIndexMeasurement.this, message, Toast.LENGTH_SHORT).show();
                }
                GarduIndexMeasurement.this.shiftUISubmit(true);
            }

            @Override public void onRequestSuccessful(int status, String message)
            {
                Timber.d("onRequestSuccessful");

                GarduIndexMeasurement.this.gpsSwitchReqeust.setChecked(false);
                this.onRequestFailed(status, message);
                GarduIndexMeasurement.this.measurement.reset();
            }
        });
    }

    private void shiftUISubmit(boolean finished)
    {
        Timber.d("shiftUISubmit");

        if(finished)
        {
            this.progress.setVisibility(View.GONE);
            this.submit.setEnabled(true);
            this.gpsSwitchReqeust.setEnabled(true);
        }
        else
        {
            this.progress.setVisibility(View.VISIBLE);
            this.submit.setEnabled(false);
            this.gpsSwitchReqeust.setEnabled(true);
        }
    }

    private void updateMap(GoogleMap map, Location o)
    {
        if(map != null)
        {
            if(o == null)
            {
                if(this.marker != null)
                {
                    this.marker.setVisible(false);
                }
            }
            else
            {
                LatLng latLng = new LatLng(o.getLatitude(), o.getLongitude());

                if(this.marker != null)
                {
                    this.marker.setPosition(latLng);
                }
                else
                {
                    this.marker = map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
                this.marker.setVisible(true);
            }
        }
    }
}
