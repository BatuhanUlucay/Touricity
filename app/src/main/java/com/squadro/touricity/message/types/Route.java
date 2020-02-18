package com.squadro.touricity.message.types;

import com.squadro.touricity.message.types.interfaces.IEntry;
import com.squadro.touricity.message.types.interfaces.IRoute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route implements IRoute {

    private String route_id;
    private String creator;
    private IEntry[] entries;
    private List<IEntry> abstractEntryList;
    private String city_id;
    private String title;
    private int privacy;

    public Route() {
        this.route_id = null;
        this.creator = null;
        this.abstractEntryList = new ArrayList<>();
        this.city_id = null;
        this.title = null;
        this.privacy = 0;
        entries = null;
    }

    public Route(String route_id, String creator, IEntry[] entries, String city_id, String title, int privacy) {
        this.route_id = route_id;
        this.creator = creator;
        this.abstractEntryList = Arrays.asList(entries);
        this.entries = entries;
        this.city_id = city_id;
        this.title = title;
        this.privacy = privacy;
    }

    public void addEntry(AbstractEntry abstractEntry){

        this.abstractEntryList.add(abstractEntry);
        this.entries = abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
    }

    public void addEntry(AbstractEntry abstractEntry, int index){
        if(index<0)
            abstractEntryList.add(0, abstractEntry);

        else if(index >= abstractEntryList.size()){
            abstractEntryList.add(abstractEntry);
        }

        else{
            this.abstractEntryList.add(index, abstractEntry);
        }
        this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
    }

    public void deleteEntry(AbstractEntry abstractEntry){
        this.abstractEntryList.remove(abstractEntry);
        this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
    }

    public void deleteEntry(int index){

        this.abstractEntryList.remove(index);
        this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
    }

    /**
     * not efficient
     *
     * @param entry_id
     * @return true when success, false when entry_id is not found.
     */
    public boolean deleteEntry(String entry_id){

        Iterator<IEntry> iter = abstractEntryList.iterator();

        while(iter.hasNext()){
            IEntry temp = iter.next();
            if(temp.getEntry_id().equals(entry_id)){
                abstractEntryList.remove(temp);
                this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
                return true;
            }
        }
        return false;
    }

    /**
     * @param abstractEntry is the abstractEntry that will be replaced.
     * @param newPos is the new position of given abstractEntry.
     * @return true when success, false when abstractEntry is not found in list (list is unchanged)
     */

    public boolean changeEntryPosition(AbstractEntry abstractEntry, int newPos){

        if(!abstractEntryList.contains(abstractEntry)){
            return false;
        }
        else{
            if(newPos < 0){
                abstractEntryList.remove(abstractEntry);
                abstractEntryList.add(0, abstractEntry);
                this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
            }
            else if(newPos >= abstractEntryList.size()){
                abstractEntryList.remove(abstractEntry);
                abstractEntryList.add(abstractEntry);
                this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
            }
            else{
                abstractEntryList.remove(abstractEntry);
                abstractEntryList.add(newPos, abstractEntry);
                this.entries = (IEntry[]) abstractEntryList.toArray(new IEntry[abstractEntryList.size()]);
            }
            return true;
        }
    }

}
