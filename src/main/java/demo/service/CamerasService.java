package demo.service;

import demo.entities.Cameras;

import java.util.List;

public interface CamerasService {
    public List<Cameras> getLists();

    public void saveCamera(Cameras cameras);

    public boolean updateCamera(Cameras cameras);

    public boolean deleteCamera(int cid);
}
