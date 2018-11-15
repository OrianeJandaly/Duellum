package fr.epsi.duellum.duellum;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;



public class GameAdapter extends ArrayAdapter<Class_Game> {

    //tweets est la liste des models à afficher
    public GameAdapter(Context context, List<Class_Game> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cellule_listview,parent, false);
        }

        JeuViewHolder viewHolder = (JeuViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new JeuViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Class_Game jeu = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.titre.setText(jeu.GetName());
        viewHolder.description.setText(jeu.GetDescription());

        return convertView;
    }

    private class JeuViewHolder {
        public TextView titre;
        public TextView description;
    }
}