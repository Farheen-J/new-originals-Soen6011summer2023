
import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import SideMenu from '../components/SideMenu';
import { AppBar, Toolbar, Typography, IconButton, Menu, MenuItem } from '@material-ui/core';
import { getUserInfo, logOut } from '../services/registerAPI';
import EmployerHP from '../components/Employer/EmployerHP';
import CandidateHP from '../components/Candidate/CandidateHP';
import { createTheme, ThemeProvider } from "@material-ui/core/styles";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import { Grid } from '@material-ui/core';

import data from "../components/JobListings/data.json";
import Jobs from "../components/JobListings/Jobs";
import TrackApplications from "../components/Candidate/TrackApplications";
import Resume from "../components/Candidate/Resume";
import Header from "../components/JobListings/Header";

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    height: '100%',
  },
  appBar: {
    height: 55,
    backgroundColor: '#fecdcc',
    flexGrow: 1,
  },
  title: {
    color: "black",
    textAlign: 'left',
    fontWeight: 900,
    marginBottom: theme.spacing(2),
    marginLeft: theme.spacing(0)
  },
  content: {
    display: 'flex',
    flexGrow: 1,
    minHeight: 0,
  },
  menuItem: {
    fontFamily: 'inherit',
    fontWeight: 'inherit',
    fontSize: 'inherit',
  },
  accountIcon: {
    color: 'black',
    fontSize: 30,
    position: "center",
    marginBottom: theme.spacing(1)
  },
  menuIconContainer: {
    position: 'absolute',
    right: 0,
  },
}));

function Home({ loginCallBack }) {
  const classes = useStyles();
  const user = getUserInfo();

  const [selectedOption, setSelectedOption] = useState('job_listings');
  const [filterKeywords, setfilterKeywords] = useState([]);

  const addFilterKeywords = (data) => {
    if (!filterKeywords.includes(data)) {
      setfilterKeywords([...filterKeywords, data]);
    }
  };

  const deleteKeyword = (data) => {
    const newKeywords = filterKeywords.filter((key) => key !== data);
    setfilterKeywords(newKeywords);
  };

  const clearAll = () => {
    setfilterKeywords([]);
  };

  let userType = user?.userType;
  const font = "League Spartan, monospace";

  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    logOut();
    loginCallBack();
  };

  const handleMenuItemClick = (option) => {
    setSelectedOption(option);
    handleClose();
  };

  const theme = createTheme({
    typography: {
      fontFamily: font,
      button: {
        textTransform: "none"
      }
    }
  });

  return (
    <>
      <ThemeProvider theme={theme}>
        <div className={classes.root}>
          <AppBar position="fixed" className={classes.appBar}>
            <Toolbar>
              <Typography variant="h4" className={classes.title}>connects.</Typography>
              <div className={classes.menuIconContainer}>
                <IconButton color="inherit" onClick={handleClick}>
                  <AccountCircleIcon className={classes.accountIcon} />
                </IconButton>
                <Menu
                  anchorEl={anchorEl}
                  keepMounted
                  open={Boolean(anchorEl)}
                  onClose={handleClose}
                >
                  <MenuItem onClick={handleClose} className={classes.menuItem}>User: {user.last_name}</MenuItem>
                  <MenuItem onClick={handleLogout} className={classes.menuItem}>Logout</MenuItem>
                </Menu>
              </div>
            </Toolbar>
          </AppBar>
          {userType !== null &&
            <Grid container className={classes.content}>
              <Grid item xs={3}>
                <SideMenu
                  loginCallBack={loginCallBack}
                  onMenuItemClick={handleMenuItemClick}
                />
              </Grid>
              <Grid item xs>
                {userType === 'employer' ? (
                  <>
                    <EmployerHP />
                  </>
                ) : null}

                {userType === 'candidate' ? (
                  <>
                    <CandidateHP />
                    {selectedOption === 'job_listings' ? (
                      <>
                        {filterKeywords.length > 0 && (
                          <Header
                            keywords={filterKeywords}
                            removeKeywords={deleteKeyword}
                            clearAll={clearAll}
                          />
                        )}
                        <Jobs
                          keywords={filterKeywords}
                          data={data}
                          setKeywords={addFilterKeywords}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'resume' ? (
                      <>
                        {filterKeywords.length > 0 && (
                          <Header
                            keywords={filterKeywords}
                            removeKeywords={deleteKeyword}
                            clearAll={clearAll}
                          />
                        )}
                        < Resume
                          keywords={filterKeywords}
                          data={data}
                          setKeywords={addFilterKeywords}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'track' ? (
                      <>
                        <TrackApplications/>
                      </>
                    ) : null}
                  </>
                ) : null}
              </Grid>
            </Grid>
          }
        </div>
      </ThemeProvider>
    </>
  );
}

export default Home;