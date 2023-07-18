
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import SideMenu from '../components/SideMenu';
import { AppBar, Box, Toolbar, Typography } from '@material-ui/core';
import { getUserInfo } from '../services/registerAPI';
import EmployerHP from '../components/Employer/EmployerHP';
import CandidateHP from '../components/Candidate/CandidateHP';

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    padding: '10px 0px'
  },
}));

function Home({ loginCallBack }) {
  const classes = useStyles();
  const user = getUserInfo();
  let userType = user?.userType;

  return (
    <>
      <Box sx={{ flexGrow: 1 }} >
        <AppBar position="static" style={{
          backgroundColor: "var(--main-color)"
        }}>
          <Toolbar>
            <Typography variant="h3" gutterBottom style={{
              color: "white",
              margin: "10px"
            }}>
              Connects
            </Typography>
          </Toolbar>
        </AppBar>
      </Box>
      {userType !== null && <div className={classes.root}>
        {userType === 'employer' ? (
          <>
            <EmployerHP />
          </>
        ) : null}

        {userType === 'candidate' ? (
          <>
            <CandidateHP />
          </>
        ) : null}

        <SideMenu loginCallBack={loginCallBack} />
      </div>}
    </>
  );
}

export default Home;