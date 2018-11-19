package fr.epsi.duellum.duellum;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ClassementAdapter extends ArrayAdapter<Class_Player> {

    public ClassementAdapter(Context context, List<Class_Player> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Class_Player joueur = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cellule_classement, parent, false);
        }
        ClassementViewHolder viewHolder = (ClassementViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ClassementViewHolder();
            (TextView)convertView.findViewById(R.id.classement_name));
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    private class ClassementViewHolder {
    }
}