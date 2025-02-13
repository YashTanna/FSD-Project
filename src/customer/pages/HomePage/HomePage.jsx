import React from "react";
import MainCarosal from "../../components/HomeCarosal/MainCarosal";
import HomeSectionCarosal from "../../components/HomeSectionCarosal/HomeSectionCarosal";

const HomePage = () => {
  return (
    <>
      <MainCarosal />
      <div className="spacey-10 py-20 flex flex-col justify-center px-5 lg:px-1">
        <HomeSectionCarosal/>
        <HomeSectionCarosal/>
        <HomeSectionCarosal/>
        <HomeSectionCarosal/>
      </div>
    </>
  );
};

export default HomePage;