package demo.repository;

import demo.entities.Groups;

import java.util.List;

public interface GroupDao {

    public List<Groups> getGroups();

    public void saveGroup(Groups group);
}
