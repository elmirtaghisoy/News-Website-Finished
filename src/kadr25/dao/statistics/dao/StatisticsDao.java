package kadr25.dao.statistics.dao;

import kadr25.model.Statistics;

import java.util.List;

public interface StatisticsDao {
    List<Statistics> getGeneralAuthorsStatistics() throws Exception;
    List<Statistics> getStatisticsByMonth(Integer month, Integer id) throws Exception;
}
