import React from 'react';
import Drawer from '@material-ui/core/Drawer';
import { makeStyles } from '@material-ui/core/styles';
import { Menu, MenuItem } from '@material-ui/core';
import { getUserInfo, logOut } from '../services/registerAPI';
import { createTheme, ThemeProvider } from "@material-ui/core/styles";

const drawerWidth = 240;

const font = "League Spartan, monospace";

const theme = createTheme({
  typography: {
    fontFamily: font,
    button: {
      textTransform: "none"
    }
  }
});

const particlesOptions = {
  background: {
    color: '#f1efe7'
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

const useStyles = makeStyles(theme => ({
  particlesContainer: {
    position: 'fixed',
    top: 0,
    left: 0,
    width: '100%',
    height: '100%',
    zIndex: -1,
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
    top: theme.mixins.toolbar.minHeight, 
    height: `calc(100% - ${theme.mixins.toolbar.minHeight})`,
    background: '#fffaf1', 
    color: 'black',
    boxShadow: '3px 0 10px rgba(0, 0, 0, 0.2)',
  },
  bigAvatar: {
    margin: 30,
    width: 100,
    height: 100,
  },
  menuItem: {
    fontFamily: 'inherit',
    fontWeight: 'inherit',
    fontSize: 'inherit',    
    transition: 'background-color 0.2s ease-in-out',
    '&:hover': {
      backgroundColor: '#ffe4e6', 
    },
  },
  firstMenuItem: {
    marginTop: theme.spacing(5), 
  },
  menu: {
    position: 'absolute',
    top: theme.mixins.toolbar.minHeight + 20,
    right: 10,
  },
}));

function SideMenu({ loginCallBack }) {
  const classes = useStyles();
  const user = getUserInfo();
  
  return (
    <Drawer
      open={true}
      variant='permanent'
      anchor='left'
      className={classes.drawer}
      classes={{
        paper: classes.drawerPaper,
      }}
    >
        <ThemeProvider theme={theme}>
          <MenuItem className={`${classes.menuItem} ${classes.firstMenuItem}`} >Option 1</MenuItem>
          <MenuItem className={classes.menuItem}>Option 2</MenuItem>
        </ThemeProvider>
    </Drawer>
  );
}

export default SideMenu;