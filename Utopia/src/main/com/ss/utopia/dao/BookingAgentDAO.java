/**
 * 
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.BookingAgent;

/**
 * @author alecs
 *
 */
public class BookingAgentDAO extends BaseDAO<BookingAgent> {

	public BookingAgentDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub

	}

	public void addAgent(BookingAgent agent) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_agent (booking_id, agent_id) VALUES (?, ?)",
				new Object[] { agent.getBookingID(), agent.getAgentID() });
	}

	public void updateBookingAgent(BookingAgent agent) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_agent set agent_id = ? where booking_id = ?",
				new Object[] { agent.getAgentID(), agent.getBookingID() });
	}

	public void deleteAgent(BookingAgent agent) throws ClassNotFoundException, SQLException {
		save("delete from booking_agent where booking_id = ?", new Object[] { agent.getBookingID() });
	}

	public List<BookingAgent> readAgent() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_agent", null);
	}

	@Override
	protected List<BookingAgent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingAgent> agents = new ArrayList<>();
		while (rs.next()) {
			BookingAgent agent = new BookingAgent();
			agent.setBookingID(rs.getInt("booking_id"));
			agent.setAgentID(rs.getInt("agent_id"));
			agents.add(agent);
		}
		return agents;
	}

}
