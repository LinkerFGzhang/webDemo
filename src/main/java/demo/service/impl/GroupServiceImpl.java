package demo.service.impl;

import demo.entities.Groups;
import demo.entities.Users;
import demo.repository.GroupDao;
import demo.repository.UserDao;
import demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Groups> getGroups() {
        return groupDao.getGroups();
    }

    @Override
    public void saveGroup(Groups group) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        if (user.getId() == 1 || user.getGroupsByGroupId().getId() == 1 || user.getGroupsByGroupId().getId() == 2) {
            groupDao.saveGroup(group);
        }
    }

    @Override
    public List<Object[]> getAccessibilityGroups() {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        String hql = null;
        if (user.getId() == 1 || user.getGroupsByGroupId().getId() == 1) {
            hql = "select g from Groups g inner join g.usersByOwnerId u order by g.id";
            return groupDao.getAccessibilityGroups(hql);
        } else if (user.getGroupsByGroupId().getId() == 2) {
            //只能自己组别下的用户
            hql = "select g from Groups g inner join g.usersByOwnerId u where u.id=" + user.getId() + " order by g.id";
            return groupDao.getAccessibilityGroups(hql);
        } else if (user.getGroupsByGroupId().getId() >= 3) {
        }
        return null;
    }

    @Override
    public boolean updateGroup(Groups newGroup) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        Users owner = userDao.getOwner(newGroup.getId());
//        System.out.println("groupserviceImpl--owner--" + owner);
        Groups oldGroup = groupDao.getGroupById(newGroup.getId());
        oldGroup.setName(newGroup.getName());
        oldGroup.setDescription(newGroup.getDescription());

        if (user.getId() == 1) {
            groupDao.updateGroup(oldGroup);
            return true;
        } else {
            if (user.getGroupsByGroupId().getId() == 1 && newGroup.getId() > 1) {
                groupDao.updateGroup(oldGroup);
                return true;
            } else if (user.getGroupsByGroupId().getId() == 2 && owner.getId() == user.getId()) {
                groupDao.updateGroup(oldGroup);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteGroup(int gid) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        Users owner = userDao.getOwner(gid);

        if (user.getId() == 1) {
            groupDao.deleteGroup(gid);
            return true;
        } else {
            if (user.getGroupsByGroupId().getId() == 1 && gid > 3) {
                groupDao.deleteGroup(gid);
                return true;
            } else if (user.getGroupsByGroupId().getId() == 2 && owner.getId() == user.getId()) {
                groupDao.deleteGroup(gid);
                return true;
            }
        }
        return false;
    }
}
