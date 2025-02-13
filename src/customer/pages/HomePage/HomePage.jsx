import React from "react";
import MainCarosal from "../../components/HomeCarosal/MainCarosal";
import HomeSectionCarosal from "../../components/HomeSectionCarosal/HomeSectionCarosal";
import { mens_kurta } from "../../../Data/mens_kurta";
import { kurtaPage1 } from "../../../Data/kurta";

const HomePage = () => {
  return (
    <>
      <MainCarosal />
      <div className="spacey-10 py-20 flex flex-col justify-center px-5 lg:px-1">
        <HomeSectionCarosal data={mens_kurta} sectionName={"Men's Kurta"}/>
        <HomeSectionCarosal data={kurtaPage1} sectionName={"Men's Shoes"}/>
        <HomeSectionCarosal data={mens_kurta} sectionName={"Men's Shirt"}/>
        <HomeSectionCarosal data={kurtaPage1} sectionName={"Women's Saree"}/>
        <HomeSectionCarosal data={kurtaPage1} sectionName={"Women's Dress"}/>
      </div>
    </>
  );
};

export default HomePage;