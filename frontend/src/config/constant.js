export const BASE_ULR = 'http://localhost:8080/v1';
export const URLS = {
    login: '/login',
    register_employer: '/register_employer',
    register_candidate: '/register_candidate',
    employer_get_all_candidates: '/get_candidate_list',
    candidate_get_job_listings: '/get_job_listings',
    candidate_apply_job: '/candidate_apply_job',
    candidate_tracking: '/candidate_track_jobs',
    candidate_resume: '/get_built_resume',
}

const apiList = {

  jobs: `${BASE_ULR}/register_job_listing`,
  uploadResume : `${BASE_ULR}/upload_resume`,
  applications: `${BASE_ULR}/get_candidate_job_tracking`,
  getMyJobs: `${BASE_ULR}/get_job_listings`,
  applicants: `${BASE_ULR}/get_candidates_apply_job`,
  build_resume: `${BASE_ULR}/build_resume`,
  get_built_resume: `${BASE_ULR}/get_built_resume`,
  get_uploaded_resume: `${BASE_ULR}/get_uploaded_resume`,
};

export default apiList;
