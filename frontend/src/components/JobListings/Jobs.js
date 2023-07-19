import React, { useEffect, useState } from "react";
import Job from "./Job";
import { makeStyles } from "@material-ui/core/styles";

const Jobs = ({ data, setKeywords, keywords }) => {
  // console.log(data);
  const [filteredData, setfilteredData] = useState([]);

  const useStyles = makeStyles((theme) => ({
    jobsContainer: {
      display: "flex",
      flexDirection: "column",
      marginTop: "calc(2vw - 4rem + 90px)",
    },
  }));

  const classes = useStyles();

  // const SearchFunc = () => {
  //   if (keywords.length > 0) {
  //     const newData = filteredData.filter((d) => {
  //       return d.position.toLocaleLowerCase().includes(keywords);
  //     });
  //     setfilteredData(newData);
  //   } else {
  //     setfilteredData(data);
  //   }
  // };

  const modifiedData = () => {
      if (keywords) {
      const newData = data.filter((d) => {
        return keywords.every((key) => {
          return (
            d.role === key ||
            d.level === key ||
            d.languages.includes(key) ||
            d.tools.includes(key)
          );
        });
      });
      setfilteredData(newData);
    } else {
      setfilteredData(data);
    }
  };

  useEffect(() => {
    modifiedData();
    // SearchFunc();
  }, [keywords]);

  return (
    <div className={classes.jobsContainer}>
      {filteredData.map((d) => {
        return <Job key={d.id} data={d} setkeywords={setKeywords}  className={classes.jobItem}/>;
      })}
    </div>
  );
};

export default Jobs;
