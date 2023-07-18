import axios from 'axios';
import { URLS,BASE_ULR } from '../config/constant';
export const login = ({ email, password, userType }) => axios.get(URLS.login, {
  params: {
    email,
    password,
    userType
  }
}).then(
  auth => {
    if (auth.data.data.logged) {
      sessionStorage.setItem("AUTH_TOKEN", JSON.stringify({ ...auth.data.data, userType }));
    }
    return auth.data.data;
  }
);

export const signUp = (userData) => {
  let register_url = URLS.register_patient;
  if (userData.userType === 'employer') {
    register_url = URLS.register_patient;
  }
  if (userData.userType === 'candidate') {
    register_url = URLS.register_counselor;
  }
  if (userData.userType === 'administrator') {
    register_url = URLS.register_manager;
  }
  return axios.post(register_url, userData).then(
    auth => {
      if (auth.data.errors === undefined) {
        sessionStorage.setItem("AUTH_TOKEN", JSON.stringify({ ...auth.data.data, userType: userData.userType }));
      } else {
        return auth.data
      }
      return auth.data;
    }
  );

}

export const logOut = () => {
  sessionStorage.removeItem("AUTH_TOKEN");
}

export const getUserInfo =  () => JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));

export const getUserTypeToDisplay=  (userData) => {
  let userType= ""
  if (userData.userType === 'employer') {
    userType = 'Employer';
  }
  if (userData.userType === 'candidate') {
    userType = 'Candidate';
  }
  if (userData.userType === 'administrator') {
    userType = 'Administrator';
  }
  return userType;
};
