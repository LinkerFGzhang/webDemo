package demo.repository;

import demo.entities.Cameras;

import java.util.List;

public interface CamerasDao {
    public List<Cameras> getLists(String hql);

    public void saveCamera(Cameras cameras);

    public void updateCamera(Cameras cameras);

    public void deleteCamera(Cameras camera);

    public Cameras getCamera(int id);

}
