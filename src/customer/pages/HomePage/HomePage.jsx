import React from "react";
import MainCarosal from "../../components/HomeCarosal/MainCarosal";
import HomeSectionCarosal from "../../components/HomeSectionCarosal/HomeSectionCarosal";

const HomePage = () => {
  return (
    <>
      <MainCarosal />
      <div>
        <HomeSectionCarosal/>
      </div>
    </>
  );
};

export default HomePage;