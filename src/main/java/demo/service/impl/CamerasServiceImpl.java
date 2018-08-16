package demo.service.impl;

import demo.entities.Cameras;
import demo.entities.Users;
import demo.repository.CamerasDao;
import demo.repository.UserDao;
import demo.service.CamerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class CamerasServiceImpl implements CamerasService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CamerasDao camerasDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Cameras> getLists() {
        Users loginUser = (Users) request.getSession().getAttribute("loginUser");
        // 查看所属者
        Users owner = userDao.getOwner(loginUser.getGroupsByGroupId().getId());
        String hql = null;

        if (loginUser.getId() == 1 || loginUser.getGroupsByGroupId().getId() == 1) {
            hql = "select c from Cameras c inner join c.usersByUserId u";
            return camerasDao.getLists(hql);
        } else if (loginUser.getGroupsByGroupId().getId() == 2) {
            // 只能查看组别所有者为自身得信息 或者 同组下得信息
            // target.user.group.ownerId == handler.userId  or target.userId == handler.userId
            hql = "select distinct c from Cameras c inner join c.usersByUserId u where u.id="
                    + loginUser.getId() + "or u.id=" + owner.getId() + " order by c.id";
            return camerasDao.getLists(hql);
        } else if (loginUser.getGroupsByGroupId().getId() > 3) {
            //只能查看自己 或者 同组下得信息
            // target.userId == handler.userId or
            //target.userId == handler.group.ownerId or
            //target.user.groupId == handler.groupId
            hql = "select c from Cameras c inner join c.usersByUserId u where u.id="
                    + loginUser.getId() + "or u.id=" +owner.getId() + " order by c.id";
            String hql1 = "select c from Cameras c inner join c.usersByUserId u where u.groupsByGroupId.id="
                    + loginUser.getGroupsByGroupId().getId() + " order by c.id";
            List<Cameras> list1 =  camerasDao.getLists(hql);
            List<Cameras> list2 =  camerasDao.getLists(hql1);
            list1.addAll(list2);
            // 去重
            HashSet temp = new HashSet(list1);
            list1.clear();
            list1.addAll(temp);
            System.out.println(list1.size());
            return list1;
        }
        return null;
    }

    @Override
    public void saveCamera(Cameras cameras) {
        System.out.println("cameraServiceImpl-----" + cameras);
        camerasDao.saveCamera(cameras);
    }

    @Override
    public boolean updateCamera(Cameras newCamera) {
//        System.out.println("cameraServiceImpl-----" + newCamera);
        Users user = (Users) request.getSession().getAttribute("loginUser");
        Cameras oldCamera = camerasDao.getCamera(newCamera.getId());
        oldCamera.setName(newCamera.getName());
        oldCamera.setDescription(newCamera.getDescription());
        oldCamera.setUrl(newCamera.getUrl());
        oldCamera.setAddress(newCamera.getAddress());
        oldCamera.setHeight(newCamera.getHeight());
        oldCamera.setWidth(newCamera.getWidth());
        oldCamera.setLongitude(newCamera.getLongitude());
        oldCamera.setLatitude(newCamera.getLatitude());
        // 文件的所有者所在组的所有者
        Users owner = userDao.getOwner(oldCamera.getUsersByUserId().getGroupsByGroupId().getId());

        // 当前用户所在组的所有者
        Users ownGroup = userDao.getOwner(user.getGroupsByGroupId().getId());
        // 超级管理员
        if (user.getId() == 1 || user.getGroupsByGroupId().getId() == 1) {
            oldCamera.setUsersByUserId(newCamera.getUsersByUserId());
            camerasDao.updateCamera(oldCamera);
            return true;
        } else if (user.getGroupsByGroupId().getId() == 2) {
            //高级用户
            if (oldCamera.getUsersByUserId().getId() == user.getId() || owner.getId() == user.getId()) {
                oldCamera.setUsersByUserId(newCamera.getUsersByUserId());
                camerasDao.updateCamera(oldCamera);
                return true;
            }
        } else if (user.getGroupsByGroupId().getId() > 3) {
            if (oldCamera.getUsersByUserId().getId() == user.getId() || oldCamera.getUsersByUserId().getId() == ownGroup.getId()
                    || oldCamera.getUsersByUserId().getGroupsByGroupId().getId() == user.getGroupsByGroupId().getId()) {
                oldCamera.setUsersByUserId(newCamera.getUsersByUserId());
                camerasDao.updateCamera(oldCamera);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCamera(int cid) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        System.out.println("deletecamera---" + user);
        Cameras camera = camerasDao.getCamera(cid);
        Users owner = userDao.getOwner(camera.getUsersByUserId().getGroupsByGroupId().getId());
        // 超级管理员
        if (user.getId() == 1 || user.getGroupsByGroupId().getId() == 1) {
            camerasDao.deleteCamera(camera);
            return true;
        } else if (user.getGroupsByGroupId().getId() == 2) {
            //高级用户
            if (camera.getUsersByUserId().getId() == user.getId() || owner.getId() == user.getId()) {
                camerasDao.deleteCamera(camera);
                return true;
            }
        } else if (user.getGroupsByGroupId().getId() > 3) {
            if (camera.getUsersByUserId().getId() == user.getId()) {
                camerasDao.deleteCamera(camera);
                return true;
            }
        }
        return false;
    }
}
