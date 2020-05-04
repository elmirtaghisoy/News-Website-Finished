package kadr25.service.statistics.service;

import kadr25.dao.statistics.dao.StatisticsDao;
import kadr25.model.Statistics;
import kadr25.service.statistics.impl.StatisticsService;

import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {
    private StatisticsDao statisticsDao;

    public StatisticsServiceImpl(StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    @Override
    public List<Statistics> getGeneralAuthorsStatistics() throws Exception {
        return statisticsDao.getGeneralAuthorsStatistics();
    }

    @Override
    public List<Statistics> getStatisticsByMonth(Integer month, Integer id) throws Exception {
        return  statisticsDao.getStatisticsByMonth(month,id);
    }
}
