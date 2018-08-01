package demo.service;

import demo.entities.Groups;

import java.util.List;

public interface GroupService {

    public List<Groups> getGroups();

    public void saveGroup(Groups group);

    public List<Object[]> getAccessibilityGroups(int uid);

    public void updateGroup(Groups group);

    public void deleteGroup(int gid);
}
