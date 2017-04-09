package vn.edu.topica.hoccontextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTopicaEdumall, btnEdumallTopica, btnTopicaAllthing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        btnTopicaAllthing = (Button) findViewById(R.id.TopicaAllthing);
        btnEdumallTopica = (Button) findViewById(R.id.EdumallTopica);
        btnTopicaEdumall = (Button) findViewById(R.id.TopicaEdumall);
        registerForContextMenu(btnEdumallTopica);
        registerForContextMenu(btnTopicaEdumall);
        registerForContextMenu(btnTopicaAllthing);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mnuInDam) {

        }
        else if(item.getItemId() == R.id.mnuMauDo) {

        }
        else if(item.getItemId() == R.id.mnuXoa) {
        }
        return super.onContextItemSelected(item);
    }
}