package huukhuong.multiplechoice;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import huukhuong.multiplechoice.adapters.AdapterSubject;
import huukhuong.multiplechoice.model.Question;
import huukhuong.multiplechoice.model.Subject;
import huukhuong.multiplechoice.util.Constant;
import huukhuong.multiplechoice.util.PlayGame;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rcvSubjects;
    private AdapterSubject adapterSubject;
    private ArrayList<Subject> listSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
    }

    private void addControls() {
        listSubject = new ArrayList<>();
        rcvSubjects = findViewById(R.id.rcvSubjects);
        adapterSubject = new AdapterSubject(this, listSubject);
        rcvSubjects.setAdapter(adapterSubject);

        OverScrollDecoratorHelper.setUpOverScroll(rcvSubjects, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        getAllSubjects();
    }

    private void getAllSubjects() {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Đang tải");
        progress.setMessage("Vui lòng đợi...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest subjectRequest = new JsonArrayRequest(Request.Method.GET,
                Constant.URL_SUBJECT,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Subject subject = new Subject();
                            subject.setId(jsonObject.getInt("id"));
                            subject.setName(jsonObject.getString("name"));
                            subject.setImage(Constant.URL_IMAGE + jsonObject.getString("image"));
                            listSubject.add(subject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapterSubject.notifyDataSetChanged();
                    progress.dismiss();

                }, error -> findViewById(R.id.err).setVisibility(View.VISIBLE)) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return null;
            }
        };

        Constant.listQuestion = new ArrayList<>();

        JsonArrayRequest questionRequest = new JsonArrayRequest(
                Request.Method.GET,
                Constant.URL_QUESTION,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Question question = new Question();
                            question.setId(jsonObject.getInt("id"));
                            question.setQuestion(jsonObject.getString("question"));
                            question.setAnsA(jsonObject.getString("ans_a"));
                            question.setAnsB(jsonObject.getString("ans_b"));
                            question.setAnsC(jsonObject.getString("ans_c"));
                            question.setAnsD(jsonObject.getString("ans_d"));
                            question.setCorrect(jsonObject.getString("result"));
                            question.setSubjectId(jsonObject.getInt("subject"));
                            Constant.listQuestion.add(question);
                        }
                    } catch (Exception e) {
                    }
                    progress.dismiss();
                },
                error -> findViewById(R.id.err).setVisibility(View.VISIBLE)
        );

        requestQueue.add(subjectRequest);
        requestQueue.add(questionRequest);
    }

}