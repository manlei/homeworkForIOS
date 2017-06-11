package dsca.cs.nju.mytabmenudemo.publish;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.model.Dish;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;
import dsca.cs.nju.mytabmenudemo.util.AppContext;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublishDish extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    private static final String TAG = "PublishDish";

    @BindView(R.id.image_publish_dish)
    ImageView imageDish;

    @BindView(R.id.iv_camera_dish)
    ImageView chooseImageDish;

    @BindView(R.id.et_title_publish_dish)
    EditText titleDish;

    @BindView(R.id.et_label_publish_dish)
    EditText labelDish;

    @BindView(R.id.et_description_publish_dish)
    EditText descriptionDish;

    @BindView(R.id.btn_publish_dish)
    Button releaseDish;

    private String imageUrl;

    private Uri imageUri;

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_dish);
        ButterKnife.bind(this);

        getPermission();

        addListener();
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique

                return;
            }
        }

    }


    private void addListener() {
        chooseImageDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGallery();
            }
        });

        //choose amn image
        imageDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGallery();

            }
        });

        releaseDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imagePath == null) {
                    Toast.makeText(getBaseContext(),"请选择图片",Toast.LENGTH_SHORT).show();
                    return;
                }
                File originFile = new File(imagePath);

                imageUrl = originFile.getName();

                Log.e("图片名称",originFile.getName());

                String title = titleDish.getText().toString();
                String label = labelDish.getText().toString();
                String description = descriptionDish.getText().toString();

                RequestBody filePart = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)),originFile);
                MultipartBody.Part file = MultipartBody.Part.createFormData("photo",originFile.getName(),filePart);
                Dish dish = new Dish(AppContext.getOnlineUser().getAccount(),AppContext.getOnlineUser().getPassword(),label,title,description,imageUrl);

                ApiClient.getUserService().addDish(dish,file).enqueue(new Callback<Dish>() {
                    @Override
                    public void onResponse(Call<Dish> call, Response<Dish> response) {
                        Log.e(TAG,"发布成功");
                        Log.e(TAG,response.body()+""+response.code());
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Dish> call, Throwable t) {
                        Log.e(TAG,"发布失败");
                    }
                });

            }
        });
    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void startGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String path = pictureDirectory.getPath();
        Uri data = Uri.parse(path);
        intent.setDataAndType(data,"image/*");
        startActivityForResult(intent,20);
        Toast.makeText(PublishDish.this,"选择图片",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 20) {
                imageUri = data.getData();
                imagePath = getPath(imageUri);

                Glide.with(getBaseContext()).load(imagePath).into(imageDish);
                Log.e("imageUri",imageUri+"");
                Log.e("imagePath:",imagePath);
            }
        }
    }

}
