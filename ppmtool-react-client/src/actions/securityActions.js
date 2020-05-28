import axios from 'axios'
import { GET_ERRORS, SET_CURRENT_USER } from "./types"
import setJwtToken from '../securityUtils/setJwtToken';
import jwt_decode from 'jwt-decode'

export const createNewUser = (newUser, history) => async dispatch =>{
    try {
        await axios.post("http://localhost:8080/api/users/register", newUser);
        history.push("/login")
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
}

export const login = LoginRequest => async dispatch => {
    try {
        
        // post Login request
        const res = await axios.post("http://localhost:8080/api/users/login", LoginRequest);
        
        // extract token from res.data
        const { token } = res.data; 
        
        // store token in localStorage
        localStorage.setItem("jwtToken", token);
        
        //set token in header for request
        setJwtToken(token);

        // decode token in React
        const decoded = jwt_decode(token);

        //dispatch to the securityReducer
        dispatch({
            type:SET_CURRENT_USER,
            payload:decoded
        })
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
        
    }
}

export const logout = () => dispatch => {
    localStorage.removeItem("jwtToken");
    setJwtToken(false);
    dispatch({
        type: SET_CURRENT_USER,
        payload: {}
    })
}