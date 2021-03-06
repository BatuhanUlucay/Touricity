package com.squadro.touricity.view.map.DirectionsAPI;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;

import com.squadro.touricity.message.types.Route;
import com.squadro.touricity.message.types.Stop;
import com.squadro.touricity.message.types.interfaces.IEntry;
import com.squadro.touricity.view.routeList.RouteCreateView;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class WaypointOrder implements IAsync {

    RouteCreateView rcw = null;
    Route route;
    List<Integer> order;
    public IAsync async;



    public WaypointOrder(String url, RouteCreateView route){
        this.async = async;
        this.rcw = route;
        FetchUrl fetchUrl = new FetchUrl(this);
        fetchUrl.execute(url);
    }

    public WaypointOrder(RouteCreateView route, List<Integer> order){
        this.rcw = route;
        this.order = order;
        routeOrder(rcw, order);
    }

    @Override
    public void onComplete(String data) {
        WaypointParser wp = new WaypointParser();
        List<Integer> waypointOrder = wp.parse(data);

        routeOrder(rcw, waypointOrder);

    }

    public void routeOrder(RouteCreateView rcw, List<Integer> order) {

        Route tmpRoute = rcw.getRoute();
        List<Stop> stopList = new ArrayList<>();

        for (IEntry entry:tmpRoute.getAbstractEntryList()) {
            if(entry instanceof Stop){
                stopList.add((Stop) entry);
            }
        }

        stopList.get(0).setIndex(0);

        for(int i = 1; i<stopList.size()-1; i++){
            stopList.get(i).setIndex(order.get(i-1));
        }

        stopList.get(stopList.size()-1).setIndex(stopList.size()-1);


        rcw.getRoute().getAbstractEntryList().clear();

        StopComparator comp = new StopComparator();
        stopList.sort(comp);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                for (Stop stop:stopList) {
                    rcw.onInsertStop(stop);
                }
            }
        });
    }
}
