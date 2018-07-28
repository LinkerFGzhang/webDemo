package demo.service.impl;

import demo.entities.Groups;
import demo.repository.GroupDao;
import demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Groups> getGroups() {
        return groupDao.getGroups();
    }

    @Override
    public void saveGroup(Groups group) {
        groupDao.saveGroup(group);
    }
}
