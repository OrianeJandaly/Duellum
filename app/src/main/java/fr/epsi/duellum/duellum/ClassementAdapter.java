package fr.epsi.duellum.duellum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            viewHolder = new ClassementAdapter.ClassementViewHolder();
            viewHolder.name = convertView.findViewById(R.id.classement_name);
            viewHolder.victoires = convertView.findViewById(R.id.classement_victoires);
            viewHolder.defaites = convertView.findViewById(R.id.classement_defaites);
            viewHolder.position = convertView.findViewById(R.id.classement_position);
            convertView.setTag(viewHolder);
        }


        viewHolder.name.setText(joueur.getName());
        if (joueur.getVictoires() > 1) {
            viewHolder.victoires.setText(joueur.getVictoires() + " Victoires");
        } else {
            viewHolder.victoires.setText(joueur.getVictoires() + " Victoire");
        }

        if (joueur.getDefaites() > 1) {
            viewHolder.defaites.setText(joueur.getDefaites() + " Défaites");
        } else {
            viewHolder.defaites.setText(joueur.getDefaites() + " Défaite");
        }

        viewHolder.position.setText((position + 1) + "");
        return convertView;

    }


    private class ClassementViewHolder {
        public TextView name;
        public TextView victoires;
        public TextView defaites;
        public TextView position;
    }
}