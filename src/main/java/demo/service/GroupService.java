package demo.service;

import demo.entities.Groups;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GroupService {

    public List<Groups> getGroups();

    public void saveGroup(Groups group);

    public List<Object[]> getAccessibilityGroups();

    public boolean updateGroup(Groups group);

    public boolean deleteGroup(int gid);

}
