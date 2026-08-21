package nullex.updater
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
class MainScreenActivity : AppCompatActivity()
{
    companion object
    {
        private const val STORAGE_PERMISSION_REQUEST = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestStoragePermission();
        requestAllFilesAccess();
        if(savedInstanceState == null) supportFragmentManager.beginTransaction()
            .replace(R.id.ThisFullscreenFragment, MainScreenFragment()).commit();
    }

    private fun requestStoragePermission()
    {
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2 &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_REQUEST
            );
        }
    }

    private fun requestAllFilesAccess()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !Environment.isExternalStorageManager())
        {
            Toast.makeText(this, "Select Null Updates and enable All files access", Toast.LENGTH_LONG).show();
            startActivity(Intent(
                Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                Uri.parse("package:$packageName")
            ));
        }
    }
}