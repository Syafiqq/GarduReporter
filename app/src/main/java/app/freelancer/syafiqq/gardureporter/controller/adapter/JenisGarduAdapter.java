package app.freelancer.syafiqq.gardureporter.controller.adapter;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 31 July 2017, 5:59 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.orm.JenisGarduOrm;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class JenisGarduAdapter extends ArrayAdapter<JenisGarduOrm> implements Filterable
{
    public JenisGarduAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<JenisGarduOrm> objects)
    {
        super(context, resource, objects);
    }

    public JenisGarduAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull JenisGarduOrm[] objects)
    {
        super(context, resource, objects);
    }

    @NonNull @Override public View getView(int position, @android.support.annotation.Nullable View convertView, @NonNull ViewGroup parent)
    {
        @NotNull
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_view, parent, false);
        TextView str = view.findViewById(R.id.dropdown_view_item);
        str.setText(this.getItem(position).getName());
        return view;
    }

    @Override public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        @NotNull
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_view, parent, false);
        TextView str = view.findViewById(R.id.dropdown_view_item);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llp.setMargins(8, 4, 4, 8); // llp.setMargins(left, top, right, bottom);
        str.setLayoutParams(llp);
        str.setText(this.getItem(position).getName());
        return view;
    }
}
