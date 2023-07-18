import React, { useState, useEffect } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import { makeStyles } from '@mui/styles';
import { Particles } from 'react-tsparticles';
import connects_logo from "../images/connects_logo.png";


import Login from '../components/login';
import Signup from '../components/signup';

const useStyles = makeStyles((theme) => ({
  particlesContainer: {
    position: 'fixed',
    top: 0,
    left: 0,
    width: '100%',
    height: '100%',
    zIndex: -1,
  },
  root: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    minHeight: '100vh',
  },
  panel: {
    //background: '#f1efe7',
    background: '#00000',
    textAlign: 'center',
    opacity: '0.8',
    width: '100%',
  },
  content: {
    width: '100%',
    maxWidth: '600px',
  },
  panelImageContainer: {
    height: '40px', // Set the height of the image container to the desired height
  },
  logo: {
    maxHeight: '100%', // Set the maximum height of the image to 100% of its parent container (image container)
    float: 'left',
  },
}));


const particlesOptions = {
  background: {
    color: '#f1efe7'
    //color: '#fffff'
    //color: '#000000',
  },
  particles: {
    number: {
      value: 100,
      density: {
        enable: true,
        value_area: 800,
      },
    },
    color: {
      value: ['#000000'],
      //value: ['#ff9000', '#ff0266', '#00ffff'],
    },
    shape: {
      type: 'circle',
    },
    size: {
      value: 3,
      random: true,
      anim: {
        enable: false,
      },
    },
    move: {
      enable: true,
      speed: 2,
      direction: 'none',
      random: true,
      straight: false,
      outModes: {
        default: 'out',
      },
      attract: {
        enable: false,
      },
    },
    links: {
      enable: true,
      color: '#000000',
      //color: '#ffffff',
      distance: 150,
      opacity: 0.4,
      width: 1,
    },
    interactivity: {
      detectsOn: 'canvas',
      events: {
        onHover: {
          enable: true,
          mode: 'repulse',
        },
      },
      modes: {
        repulse: {
          distance: 200,
          duration: 0.4,
        },
      },
    },
  },
  detectRetina: true,
};

const defaultTheme = createTheme();

const SignInOutContainer = ({ loginCallBack }) => {
  const classes = useStyles();
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Grid container component="main" sx={{ height: '100vh', justifyContent: 'center', alignItems: 'center' }}>
        <CssBaseline />
        <Grid item xs={false} sm={4} md={7} />
        <div className={classes.particlesContainer}>
          <Particles params={particlesOptions} />
        </div>
        <Box sx={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          {/* Panel at the top */}
          <Paper elevation={3} className={classes.panel}>
            <div className={classes.panelImageContainer}>
              <img
                src={connects_logo}
                className={classes.logo}
                alt="Main Logo"
              />
            </div>
          </Paper>

          <Box sx={{ width: '100%', display: 'flex', justifyContent: 'center' }}>
            <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} className="transparent" square sx={{ height: '100vh', background: 'rgba(255, 255, 255)' }} >
              <Box
                sx={{
                  my: 8,
                  mx: 4,
                  display: 'flex',
                  flexDirection: 'column',
                  alignItems: 'center',
                }}
              >
                <Box className="signinContainer" component="form" noValidate onSubmit={handleSubmit} width="100%" >
                  <Box sx={{ mt: 0 }}>
                    <Tabs
                      value={value}
                      onChange={handleChange}
                      variant="fullWidth"
                      indicatorColor="primary" // Change the indicator color
                    >
                      <Tab
                        label="Sign In"
                        sx={{
                          color: 'black',
                          backgroundColor: 'rgba(255, 255, 255, 0.3)',
                          borderTop: '1px solid rgba(0, 0, 0, 0.2)',
                          borderBottom: '1px solid rgba(0, 0, 0, 0.2)',
                          '&:hover': {
                            color: 'black',
                            backgroundColor: 'rgba(255, 255, 255, 0.1)',
                            transition: 'background-color 0.3s ease',
                          },
                        }}
                      />
                      <Tab
                        label="Sign Up"
                        sx={{
                          color: 'black',
                          backgroundColor: 'rgba(255, 255, 255, 0.1)',
                          borderTop: '1px solid rgba(0, 0, 0, 0.2)',
                          borderBottom: '1px solid rgba(0, 0, 0, 0.2)',
                          '&:hover': {
                            color: 'black',
                            backgroundColor: 'rgba(255, 255, 255, 0.1)',
                            transition: 'background-color 0.3s ease',
                          },
                        }}
                      />
                    </Tabs>

                    <TabPanel value={value} index={0}>
                      <Login handleChange={() => { }} loginCallBack={loginCallBack} />
                    </TabPanel>
                    <TabPanel value={value} index={1}>
                      <Signup handleChange={() => { }} loginCallBack={loginCallBack} />
                    </TabPanel>
                  </Box>
                </Box>
              </Box>
            </Grid>

          </Box>
        </Box>

      </Grid>
    </ThemeProvider>
  );
};

const TabPanel = ({ children, value, index }) => {
  return (
    <Box
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${index}`}
      sx={{ width: '100%', pt: 3 }}
    >
      {value === index && <Box>{children}</Box>}
    </Box>
  );
};

export default SignInOutContainer;