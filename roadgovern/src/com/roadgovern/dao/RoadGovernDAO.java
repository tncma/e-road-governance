package com.roadgovern.dao;

import java.util.List;

import com.roadgovern.vo.CityVO;
import com.roadgovern.vo.ComplaintVO;
import com.roadgovern.vo.DistrictVO;
import com.roadgovern.vo.IssueTypeVO;
import com.roadgovern.vo.RoadVO;
import com.roadgovern.vo.StateVO;
import com.roadgovern.vo.UserVO;



/**
 * @author arun_christopher
 *
 */
public interface RoadGovernDAO {
	
	
	
	public static final String RETRIEVE_STATES	=	"SELECT state_id, state_name FROM state";
	public static final String RETRIEVE_DISTRICTS	=	"SELECT district_id, district_name from district";
	public static final String RETRIEVE_CITIES	=	"SELECT city_id, city_name from city";
	public static final String RETRIEVE_ROADS	=	"SELECT DISTINCT road_id, road_name from road";
	public static final String RETRIEVE_ISSUE_TYPES	=	"SELECT DISTINCT issue_type_id, issue_type_desc from issue_type";
	public static final String RETRIEVE_USER	=	"SELECT user_id, first_name, last_name, role, password, create_ts, reportee, email, phone from users WHERE user_id=? and password=?";
	public static final String SAVE_USER	=	"INSERT INTO users values(?,?,?,?,?,?,?,?,?)";
	
	public List<StateVO> getStates();


	public List<CityVO> getCities(int districtId);


	public List<DistrictVO> getDistricts(int stateId);


	public List<RoadVO> getRoads(int cityId);


	public List<IssueTypeVO> getIssuseTypes();


	public boolean logon(UserVO userVO);


	public void saveUser(UserVO userVO);


}
