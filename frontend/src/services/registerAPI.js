import axios from 'axios';
import { URLS, BASE_ULR } from '../config/constant';
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
  let register_url = URLS.register_employer;

  let data = {
    name: userData.name,
    last_name: userData.last_name,
    phone_number: userData.phone_number,
    email_address: userData.email_address,
    password: userData.password
  }

  if (userData.userType === 'employer') {
    register_url = URLS.register_employer;
    data.company_name = userData.company_name
    data.designation = userData.designation
    data.registration_number = userData.registration_number
  }
  if (userData.userType === 'candidate') {
    register_url = URLS.register_candidate;
    data.age = userData.age
    data.gender = userData.gender
  }
  console.log("data " + JSON.stringify(data))
  return axios.post(register_url, data).then(
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

export const getUserInfo = () => JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));

export const jobListings = () => {
  let register_url = URLS.candidate_get_job_listings;
  return axios.get(register_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const applyCandidateJobs = (userData) => {
  let register_url = URLS.candidate_apply_job;
  return axios.post(register_url, userData).then(
    auth => {
      return auth.data;
    }
  );

}
