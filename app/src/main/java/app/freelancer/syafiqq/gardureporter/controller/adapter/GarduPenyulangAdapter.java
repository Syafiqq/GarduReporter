package app.freelancer.syafiqq.gardureporter.controller.adapter;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 30 July 2017, 11:49 PM.
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduPenyulangOrm;
import com.toptoche.searchablespinnerlibrary.DialogArrayAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class GarduPenyulangAdapter extends DialogArrayAdapter<GarduPenyulangOrm> implements Filterable
{
    private final Filter filter;
    private List<GarduPenyulangOrm> lists;
    private List<GarduPenyulangOrm> usage;

    public GarduPenyulangAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<GarduPenyulangOrm> objects)
    {
        super(context, resource, objects);
        this.lists = new ArrayList<>(objects);
        this.usage = new ArrayList<>(objects);
        this.filter = new GarduPenyulangFilter(this, this.lists);
    }

    @NonNull @Override public View getView(int position, @android.support.annotation.Nullable View convertView, @NonNull ViewGroup parent)
    {
        @NotNull
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_view, parent, false);
        TextView str = view.findViewById(R.id.dropdown_view_item);
        str.setText(this.getItem(position).getName());
        return view;
    }

    @NonNull @Override public Filter getFilter()
    {
        return this.filter;
    }

    @Override public int getCount()
    {
        return this.usage.size();
    }

    @Nullable @Override public GarduPenyulangOrm getItem(int position)
    {
        return this.usage.get(position);
    }

    @Override public int getDialogCount()
    {
        return this.lists.size();
    }

    @Override public GarduPenyulangOrm getDialogItem(int position)
    {
        return this.lists.get(position);
    }

    public void update(List<GarduPenyulangOrm> lists)
    {
        this.lists.clear();
        this.lists.addAll(lists);
        this.updateFilter(lists);
    }

    private void updateFilter(List<GarduPenyulangOrm> lists)
    {
        this.usage.clear();
        this.usage.addAll(lists);
    }

    public class GarduPenyulangFilter extends Filter
    {
        final ArrayAdapter adapter;
        final List<GarduPenyulangOrm> list;

        public GarduPenyulangFilter(ArrayAdapter adapter, List<GarduPenyulangOrm> list)
        {
            this.adapter = adapter;
            this.list = list;
        }

        @Override
        protected FilterResults performFiltering(CharSequence query)
        {
            final List<GarduPenyulangOrm> filtered = new LinkedList<>();
            final FilterResults results = new FilterResults();
            if(query.length() == 0)
            {
                filtered.addAll(this.list);
            }
            else
            {
                final String filterPattern = query.toString().toLowerCase();
                for(final GarduPenyulangOrm list : this.list)
                {
                    if(Pattern.matches(".*" + filterPattern + ".*", list.getName().toLowerCase()))
                    {
                        filtered.add(list);
                    }
                }
            }
            results.values = filtered;
            results.count = filtered.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            if(adapter instanceof GarduPenyulangAdapter)
            {
                ((GarduPenyulangAdapter) this.adapter).updateFilter((List<GarduPenyulangOrm>) filterResults.values);
                this.adapter.notifyDataSetChanged();
            }
        }
    }
}