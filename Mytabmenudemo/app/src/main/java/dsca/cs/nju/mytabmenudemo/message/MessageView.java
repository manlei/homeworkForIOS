package dsca.cs.nju.mytabmenudemo.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.notification.Notification;

public class MessageView extends AppCompatActivity {

    private static final String TAG = "MessageView";

    @BindView(R.id.title_message)
    TextView title;

    @BindView(R.id.content_message)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ButterKnife.bind(this);

        Notification notification = (Notification) getIntent().getSerializableExtra(TAG);
        title.setText(notification.getTitle());
        content.setText(notification.getContent());
    }

    public static void startActivityWithParameter(Context context,Notification notification) {
        Intent intent = new Intent(context,MessageView.class);
        intent.putExtra(TAG,notification);
        context.startActivity(intent);
    }
}
