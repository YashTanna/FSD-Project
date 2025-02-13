import React from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "../HomeSectionCard/HomeSectionCard";
import { Button } from "@mui/material";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";

export default function HomeSectionCarosal() {
  const [activeIndex, setActiveIndex] = React.useState(0);

  const responsive = {
    0: { items: 1 },
    720: { items: 3 },
    1024: { items: 5.5 },
  };

  // Ensure the activeIndex stays within bounds
  const slidPrev = () => setActiveIndex(activeIndex-1);
  const slidNext = () => setActiveIndex(activeIndex+1);

  const syncActiveIndex = ({ item }) => setActiveIndex(item);

  const items = [1, 1, 1, 1, 1, 1, 1, 1].map((_, index) => (
    <HomeSectionCard key={index} counter={index + 1} />
  ));

  return (
    <div className="relative px-4 lg:px-8">
      <div className="relative p-5">
        <AliceCarousel
          items={items}
          disableButtonsControls
          infinite
          responsive={responsive}
          onSlideChanged={syncActiveIndex}
          activeIndex={activeIndex}
        />

        {/* Next Button */}
        {activeIndex < items.length - 1 && (
          <Button
            onClick={slidNext}
            className="z-50"
            variant="contained"
            sx={{
              position: "absolute",
              top: "8rem",
              right: "0rem",
              transform: "translate(50%) rotate(90deg)",
              backgroundColor: "white",
              "&:hover": { backgroundColor: "blue" },
            }}
            aria-label="next"
          >
            <KeyboardArrowLeftIcon
              sx={{ color: "black", transform: "rotate(90deg)" }}
            />
          </Button>
        )}

        {/* Prev Button */}
        {activeIndex > 0 && (
          <Button
            onClick={slidPrev}
            className="z-50"
            variant="contained"
            sx={{
              position: "absolute",
              top: "8rem",
              left: "0rem",
              transform: "translate(-50%) rotate(90deg)",
              backgroundColor: "white",
              "&:hover": { backgroundColor: "blue" },
            }}
            aria-label="prev"
          >
            <KeyboardArrowLeftIcon
              sx={{ color: "black", transform: "rotate(-90deg)" }}
            />
          </Button>
        )}
      </div>
    </div>
  );
}
