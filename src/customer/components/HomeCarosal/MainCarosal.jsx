import React from 'react';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import { homeCarouselData } from './homeCarouselData';


export const items = homeCarouselData.map((i)=> <img src={i.image} alt='' className='cursor-pointer' role='presentation'/>)

const MainCarosal = () => (
    <AliceCarousel
        items={items}
        disableButtonsControls
        autoPlay
        autoPlayInterval={2000}
        infinite
    />
);

export default MainCarosal;