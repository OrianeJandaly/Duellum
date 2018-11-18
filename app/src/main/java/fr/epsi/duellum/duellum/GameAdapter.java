package fr.epsi.duellum.duellum;

import android.content.Context;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class GameAdapter extends ArrayAdapter<Class_Game> {

    public GameAdapter(Context context, List<Class_Game> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Class_Game jeu = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cellule_listview, parent, false);
        }

        JeuViewHolder viewHolder = (JeuViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new JeuViewHolder();
            viewHolder.layout = (ConstraintLayout) convertView.findViewById(R.id.cell_layout);
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        }
        final JeuViewHolder viewHolder2 = viewHolder;
        final View convertView2 = convertView;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jeu.isEnabled()) {
                    jeu.setEnabled(false);
                    viewHolder2.titre.setTextColor(convertView2.getResources().getColor(R.color.Couleur1));
                    viewHolder2.description.setTextColor(convertView2.getResources().getColor(R.color.Couleur1));
                    viewHolder2.layout.setBackgroundColor(convertView2.getResources().getColor(R.color.Couleur2));
                } else {
                    jeu.setEnabled(true);
                    viewHolder2.titre.setTextColor(convertView2.getResources().getColor(R.color.Couleur3));
                    viewHolder2.description.setTextColor(convertView2.getResources().getColor(R.color.Couleur3));
                    viewHolder2.layout.setBackgroundColor(convertView2.getResources().getColor(R.color.Couleur1));
                }


            }
        });
        viewHolder.image.setBackgroundResource(jeu.getImage());
        viewHolder.titre.setText(jeu.getName());
        viewHolder.description.setText(jeu.getDescription());

        return convertView;
    }

    private class JeuViewHolder {
        public ConstraintLayout layout;
        public ImageView image;
        public TextView titre;
        public TextView description;
    }
}