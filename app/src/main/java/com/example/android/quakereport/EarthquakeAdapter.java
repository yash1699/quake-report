package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *
     * @param context of the app
     * @param earthquakes is the list of earthquakes which is the data source of the adapter
     */
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item (called convertView) that we can reuse,
        //otherwise if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Find the earthquake at given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);

//        Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView with view ID primary_location
        TextView primaryLocationView = listItemView.findViewById(R.id.primary_location);

        // Find the TextView with view ID location_offset
        TextView locationOffsetView = listItemView.findViewById(R.id.location_offset);

        // Find the TextView with view ID date
        TextView dateView = listItemView.findViewById(R.id.date);

        // Find the TextView with view ID time
        TextView timeView = listItemView.findViewById(R.id.time);

        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        // Format the location in desired format
        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation, locationOffset;
        // If the originalLocation String has the word "of" in it then
        // split the location into two parts first part before the word "of" and
        // second part after the word "of"
        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        // Else the first part of the location will be "Near the" and
        // second part will be the originalLocation
        else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date date = new Date(currentEarthquake.getTimeInMilliSeconds());
        // Format the date String
        String formattedDate = formatDate(date);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Format the date string to store time
        String formattedTime = formatTime(date);
        // Display the time of the current earthquake in that view
        timeView.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId ;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted date String from a date object
     */
    private String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    /**
     * Return the formatted time String from the date object
     */
    private String formatTime(Date date){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }

    /**
     * Returns the formatted magnitude String
     */
    private String formatMagnitude(double magnitude){
        return new DecimalFormat("0.0").format(magnitude);
    }
}
