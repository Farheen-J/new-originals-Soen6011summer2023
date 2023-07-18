
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import SideMenu from '../components/SideMenu';
import { AppBar, Box, Toolbar, Typography } from '@material-ui/core';
import { getUserInfo } from '../services/registerAPI';

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

          </>
        ) : null}

        {userType === 'candidate' ? (
          <>

          </>
        ) : null}

        {userType === 'administrator' ? (

          <>

          </>
        ) : null}

        <SideMenu loginCallBack={loginCallBack} />
      </div>}
    </>
  );
}

export default Home;