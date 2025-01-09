import React from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "../HomeSectionCard/HomeSectionCard";
// import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';

export default function HomeSectionCarosal() {
  const responsive = {
    0: { items: 1 },
    720: { items: 3 },
    1024: { items: 5.5 },
  };

  const items = [1, 1, 1, 1, 1, 1, 1, 1].map(() => <HomeSectionCard />);
  return (
    <div className="relative px-4 lg:px-8">
      <div className="relative p-5 ">
        <AliceCarousel
          items={items}
          disableButtonsControls
          responsive={responsive}
        />
        {/* <Button className='z-50' varient='contained' sx={{position:'absolute',top:'8rem',right:'0rem'}} aria-label='next' >
            <ChevronLeftIcon/>
        </Button> */}
      </div>
    </div>
  );
}
