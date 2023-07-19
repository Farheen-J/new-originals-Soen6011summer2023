import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";

const Job = (props) => {
  const {
    company,
    contract,
    featured,
    id,
    languages,
    level,
    location,
    logo,

    position,
    postedAt,
    role,
    tools,
  } = props.data;

  let keywords = [role, level, ...languages, ...tools];

  const [icon, setIcon] = useState("");

  const importSvgs = () => {
    const logoSvg = import(`${logo}`).then((d) => {
      setIcon(d.default);
    });
  };

  useEffect(() => {
    importSvgs();
  }, [logo]);

  const useStyles = makeStyles((theme) => ({
    part2: {
      display: "flex",
      flexWrap: "wrap",
      justifyContent: "space-between",
      // Add any styles specific to part2 (if needed)
      [theme.breakpoints.down("sm")]: {
        borderTop: `1px solid ${theme.palette.grey[700]}`,
        paddingTop: "1rem",
        justifyContent: "flex-start",
      },
    },
    span: {
      "&:not(:last-child)": {
        marginRight: "1rem",
      },
      "&:hover": {
        backgroundColor: theme.palette.primary.main,
        color: "#fff",
      },
      [theme.breakpoints.down("sm")]: {
        marginBottom: "1rem",
      },
      backgroundColor: theme.palette.background.default,
      color: theme.palette.primary.main,
      fontWeight: 700,
      padding: "0.5rem",
      borderRadius: "5px",
      cursor: "pointer",
    },
  }));

  const classes = useStyles();

  return (
    <div
      className={
        featured ? "job-container job-container--borderLeft" : "job-container"
      }
    >
      <div className="logo">
        <img src={icon} alt="" />
      </div>
      <div className="part1">
        <div className="company">
          <span className="cname">{company}</span>
          {props.data.new && <span className="new">new!</span>}
          {props.data.featured && <span className="featured">featured</span>}
        </div>

        <div className="position">{position}</div>

        <div className="details">
          <span>{postedAt}</span>
          <span>&nbsp;•&nbsp;</span>
          <span>{contract}</span>
          <span>&nbsp;•&nbsp;</span>
          <span>{location}</span>
        </div>
      </div>

      <div className={classes.part2}>
        {keywords.map((key, id) => (
          <span className={classes.span} onClick={() => props.setkeywords(key)} key={id}>
            {key}
          </span>
        ))}
      </div>
    </div>
  );
};

export default Job;
