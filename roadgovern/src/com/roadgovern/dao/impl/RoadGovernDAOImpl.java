/**
 * 
 */
package com.roadgovern.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.roadgovern.dao.BaseDAO;
import com.roadgovern.dao.RoadGovernDAO;
import com.roadgovern.hbm.IssuePoc;
import com.roadgovern.hbm.IssuePocId;
import com.roadgovern.hbm.Users;
import com.roadgovern.vo.CityVO;
import com.roadgovern.vo.DistrictVO;
import com.roadgovern.vo.IssueTypeVO;
import com.roadgovern.vo.RoadVO;
import com.roadgovern.vo.StateVO;
import com.roadgovern.vo.UserVO;

/**
 * @author Prakash
 *
 */
public class RoadGovernDAOImpl extends BaseDAO implements RoadGovernDAO {

	@Override
	public List<StateVO> getStates() {

		return this.getJdbcTemplate().query(RETRIEVE_STATES, null, null, new RowMapper<StateVO>(){
			public StateVO mapRow(ResultSet rs, int rowNum)
			throws SQLException{
				StateVO	state	=	new StateVO();
				state.setStateId(rs.getInt("state_id"));
				state.setStateName(rs.getString("state_name").trim());
				return state;
			}
		});
	}

	@Override
	public List<CityVO> getCities(final int districtId) {

		String sql	=	RETRIEVE_CITIES;
		
		if(districtId!=0){
			sql	=	RETRIEVE_CITIES+" WHERE district_Id='"+districtId+"'";
		}
		System.out.println(sql);
		
		return this.getJdbcTemplate().query(sql, null, null, new RowMapper<CityVO>(){
			public CityVO mapRow(ResultSet rs, int rowNum)
			throws SQLException{
				
				CityVO	city	=	new CityVO();
				city.setCityId(rs.getInt("city_id"));
				city.setCityName(rs.getString("city_name").trim());
				return city;
			}
		});
	}

	@Override
	public List<DistrictVO> getDistricts(int stateId) {


		String sql	=	RETRIEVE_DISTRICTS;
		
		if(stateId!=0){
			sql	=	RETRIEVE_DISTRICTS+" WHERE state_Id='"+stateId+"'";
		}
		System.out.println(sql);
		
		return this.getJdbcTemplate().query(sql, null, null, new RowMapper<DistrictVO>(){
			public DistrictVO mapRow(ResultSet rs, int rowNum)
			throws SQLException{
				
				DistrictVO	district	=	new DistrictVO();
				district.setDistrictId(rs.getInt("district_id"));
				district.setDistrictName(rs.getString("district_name").trim());
				return district;
			}
		});
	}

	@Override
	public List<RoadVO> getRoads(int cityId) {


		String sql	=	RETRIEVE_ROADS;
		
		if(cityId!=0){
			sql	=	RETRIEVE_ROADS+" WHERE city_id='"+cityId+"' ORDER BY road_name";
		}
		System.out.println(sql);
		
		return this.getJdbcTemplate().query(sql, null, null, new RowMapper<RoadVO>(){
			public RoadVO mapRow(ResultSet rs, int rowNum)
			throws SQLException{
				
				RoadVO	road	=	new RoadVO();
				road.setRoadId(rs.getInt("road_id"));
				road.setRoadName(rs.getString("road_name").trim());
				return road;
			}
		});
	}

	@Override
	public List<IssueTypeVO> getIssuseTypes() {

		return this.getJdbcTemplate().query(RETRIEVE_ISSUE_TYPES, null, null, new RowMapper<IssueTypeVO>(){
			public IssueTypeVO mapRow(ResultSet rs, int rowNum)
			throws SQLException{
				IssueTypeVO	issueType	=	new IssueTypeVO();
				issueType.setIssueTypeId(rs.getInt("issue_type_id"));
				issueType.setIssueTypeName(rs.getString("issue_type_desc").trim());
				return issueType;
			}
		});
	}

	@Override
	public boolean logon(final UserVO userVO) {


		Object[] args = new Object[2];
		int[] argTypes	=	new int[2];
		
		try{
			args[0]	=	userVO.getUserId();
			argTypes[0]	=	Types.VARCHAR;
			args[1]	=	userVO.getPassword();
			argTypes[1]	=	Types.VARCHAR;
			
			List<UserVO>  users	=	this.getJdbcTemplate().query(RETRIEVE_USER, args, argTypes, new RowMapper<UserVO>(){
				public UserVO mapRow(ResultSet rs, int rowNum)
				throws SQLException{
					userVO.setUserId(rs.getString("user_id"));
					userVO.setFirstName(rs.getString("first_name").trim());
					userVO.setLastName(rs.getString("last_name").trim());
					userVO.setUserRole(rs.getString("role").trim());
					userVO.setReportee(rs.getString("reportee").trim());
					userVO.setEmail(rs.getString("email").trim());
					userVO.setContactNo(rs.getString("phone").trim());
					return userVO;
				}
			});
			if(users.size()==1)
				return true;
			else
				return false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public void saveUser(UserVO userVO) {
		
		/*Users user	=	new Users();
		user.setUserId(userVO.getUserId());
		user.setCreateTs(new Date());
		user.setEmail(userVO.getEmail());
		user.setPhone(userVO.getContactNo());
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setPassword(userVO.getUserId());
		user.setReportee(userVO.getReportee());
		user.setRole("OFFICER");
		this.getHibernateTemplate().save(user);*/
		/*Object[] args = new Object[9];
		int[] argTypes	=	new int[9];
		
		try{
			args[0]	=	userVO.getUserId();
			argTypes[0]	=	Types.VARCHAR;
			args[1]	=	userVO.getFirstName();
			argTypes[1]	=	Types.VARCHAR;
			args[2]	=	userVO.getLastName();
			argTypes[2]	=	Types.VARCHAR;
			args[3]	=	"OFFICER";
			argTypes[3]	=	Types.VARCHAR;
			args[4]	=	userVO.getPassword();
			argTypes[4]	=	Types.VARCHAR;
			args[5]	=	userVO.getPassword();
			argTypes[5]	=	Types.VARCHAR;
			args[6]	=	userVO.getPassword();
			argTypes[6]	=	Types.VARCHAR;
			args[7]	=	userVO.getPassword();
			argTypes[7]	=	Types.VARCHAR;
			args[8]	=	userVO.getPassword();
			argTypes[8]	=	Types.VARCHAR;
			args[9]	=	userVO.getPassword();
			argTypes[9]	=	Types.VARCHAR;
			
			this.getJdbcTemplate().update(SAVE_USER, args, argTypes);
			if(users.size()==1)
				return true;
			else
				return false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		IssuePoc issuePOC =	new IssuePoc();
		City city = new City();	
		city.setCityId(userVO.getCityId());
		issuePOC.setCity(city);
		
		IssuePocId id	=	new IssuePocId();
		id.setCityId(userVO.getCityId());
		id.setIssueTypeId(userVO.getIssueTypeId());
		id.setUserId(userVO.getUserId());
		issuePOC.setId(id);
		this.getHibernateTemplate().save(issuePOC);*/
		
		
		
		
	}

}
