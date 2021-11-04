package huukhuong.multiplechoice.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;
import huukhuong.multiplechoice.PlayActivity;
import huukhuong.multiplechoice.R;
import huukhuong.multiplechoice.model.Question;
import huukhuong.multiplechoice.model.Subject;
import huukhuong.multiplechoice.util.Constant;
import huukhuong.multiplechoice.util.PlayGame;

public class AdapterSubject extends RecyclerView.Adapter<AdapterSubject.SubjectViewHolder> {

    private ArrayList<Subject> listSubject;
    private Context context;

    public AdapterSubject(Context context, ArrayList<Subject> listSubject) {
        this.context = context;
        this.listSubject = listSubject;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = listSubject.get(position);
        Picasso.get().load(subject.getImage()).error(R.drawable.no_image).into(holder.imgSubject);
        holder.txtSubjectName.setText(subject.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestions(subject.getId());
            }
        });
    }

    private void generateQuestions(int id) {
        Collections.shuffle(Constant.listQuestion);
        PlayGame.questionPlay = new ArrayList<>();

        int n = 0;
        for (Question question : Constant.listQuestion) {
            if (n == 20) break;
            if (question.getSubjectId() == id) {
                PlayGame.questionPlay.add(question);
                n++;
            }
        }

        Collections.shuffle(PlayGame.questionPlay);

        context.startActivity(new Intent(context, PlayActivity.class));
    }

    @Override
    public int getItemCount() {
        return listSubject.size();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSubjectName;
        private CircleImageView imgSubject;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSubject = itemView.findViewById(R.id.imgSubject);
            txtSubjectName = itemView.findViewById(R.id.txtSubjectName);
        }
    }
}
