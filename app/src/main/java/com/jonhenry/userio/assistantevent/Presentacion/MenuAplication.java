package com.jonhenry.userio.assistantevent.Presentacion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jonhenry.userio.assistantevent.MainActivity;
import com.jonhenry.userio.assistantevent.R;
import com.squareup.picasso.Picasso;

public class MenuAplication extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView foto;

    private static boolean salir;

    private static Bundle bundle;

    private static String nombre;

    private static String url_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aplication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Assistan Events");

        bundle=getIntent().getExtras();
        nombre=bundle.getString("NOMBRE");
        url_foto=bundle.getString("FOTO");



/*Es codigo del boton flotante
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

/*Para poder modificar el nombr del usuario
        TextView nav_nombre=(TextView) hView.findViewById(R.id.user_name);
        nav_nombre.setText(nombre);*/


        View hView =  navigationView.getHeaderView(0);

        TextView nav_nombre=(TextView) hView.findViewById(R.id.user_name);

        nav_nombre.setText(nombre);

        foto=(ImageView)hView.findViewById(R.id.foto_user);
        DescargaImagen mostrar =new DescargaImagen();
        mostrar.execute();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aplication, menu);
        return true;
    }*/

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.4
        Fragment fragment=null;
        boolean FragmentTransaction=false;
        int id = item.getItemId();


        if (id == R.id.nav_deportes) {

            FragmentTransaction=true;
            // Handle the camera action
        } else if (id == R.id.nav_juegos) {
            FragmentTransaction=true;

        } else if (id == R.id.nav_musica) {
            FragmentTransaction=true;

        } else if (id == R.id.nav_teatro_danza) {
            FragmentTransaction=true;

        } else if (id == R.id.nav_cerrar_sesion) {
            goMainActivity();

        }else if(id==R.id.nav_informacion){
            FragmentTransaction=true;
            fragment=new InformationFragment();
        }

        if (FragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }


    public void goMainActivity(){
        Intent intent= new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    class DescargaImagen extends AsyncTask<Void, Void, Void> {
        Bitmap descarga=null;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                descarga= Picasso.with(MenuAplication.this).load(url_foto).get();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(descarga!=null){
                RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), descarga);
                roundedDrawable.setCornerRadius(descarga.getHeight());
                foto.setImageDrawable(roundedDrawable);
            }
            super.onPostExecute(aVoid);
        }
    }


}
