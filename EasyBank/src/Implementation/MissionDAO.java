package Implementation;

import dto.Mission;
import interfaces.MissionI;

import java.util.List;

public class MissionDAO implements MissionI {
    @Override
    public Mission add(Mission mission) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Mission> showList() {
        return null;
    }

    @Override
    public List<Mission> missionHistory() {
        return null;
    }

    @Override
    public List<Mission> missionStatistics() {
        return null;
    }
}
