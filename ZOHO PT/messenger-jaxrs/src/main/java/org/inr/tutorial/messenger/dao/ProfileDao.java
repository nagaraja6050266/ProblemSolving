package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileDao {
    private Map<Integer, Profile> profiles= Database.getProfiles();

    public List<Profile> getAllProfiles(){
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfileById(int id){
        return profiles.get(id);
    }

    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        profiles.put(profile.getId(),profile);
        return profile;
    }

    public Profile editProfile(int id,Profile profile){
        profiles.put(id,profile);
        return profile;
    }

    public Profile deleteProfile(int id){
        return profiles.remove(id);
    }

}
