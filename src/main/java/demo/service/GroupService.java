package demo.service;

import demo.entities.Groups;

import java.util.List;

public interface GroupService {

    public List<Groups> getGroups();

    public void saveGroup(Groups group);
}
