import React from 'react'

function Items({ image, description }) {
    return (
        <div className="bg-white">
            <div className="mx-auto max-w-2xl px-4 py-16 sm:px-6 sm:py-24 lg:max-w-7xl lg:px-8">
                <div className="mt-6 grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8">
                    <div className="group relative">
                        <img src={tractor} className="aspect-square w-full rounded-md bg-white-200  group-hover:opacity-75 lg:aspect-auto lg:h-80 object-contain" />
                        <div className="mt-4 flex justify-between">
                            <div>
                                <h3 className="text-sm text-gray-700">
                                    <a href="#">
                                        <span aria-hidden="true" className="text-center font-extrabold ml-24"></span>
                                        Tractor
                                    </a>

                                </h3>
                                {/* <p className="mt-1 text-sm text-gray-500">Tractor</p> */}
                            </div>
                        </div>
                    </div>
                    <div className="group relative">
                        <img src={tractor} className="aspect-square w-full rounded-md bg-white-200 object-contain group-hover:opacity-75 lg:aspect-auto lg:h-80" />
                        <div className="mt-4 flex justify-between">
                            <div>
                                <h3 className="text-sm text-gray-700">
                                    <a href="#">
                                        <span aria-hidden="true" className="text-center font-extrabold ml-24"></span>
                                        Harvestor
                                    </a>
                                </h3>
                                {/* <p className="mt-1 text-sm text-gray-500">Black</p> */}
                            </div>
                        </div>
                    </div>
                    <div className="group relative">
                        <img src={mini} className="aspect-square w-full rounded-md bg-white-200 object-contain group-hover:opacity-75 lg:aspect-auto lg:h-80" />
                        <div className="mt-4 flex justify-between">
                            <div>
                                <h3 className="text-sm text-gray-700">
                                    <a href="#">
                                        <span aria-hidden="true" className="text-center font-extrabold ml-24"></span>
                                        Mini Tractor
                                    </a>
                                </h3>
                                {/* <p className="mt-1 text-sm text-gray-500">Black</p> */}
                            </div>
                        </div>
                    </div>
                    <div className="group relative">
                        <img src={mini} className="aspect-square w-full rounded-md bg-white-200 object-contain group-hover:opacity-75 lg:aspect-auto lg:h-80" />
                        <div className="mt-4 flex justify-between">
                            <div>
                                <h3 className="text-sm text-gray-700">
                                    <a href="#">
                                        <span aria-hidden="true" className="text-center font-extrabold ml-24"></span>
                                        Mini Tractor
                                    </a>
                                </h3>
                                {/* <p className="mt-1 text-sm text-gray-500">Black</p> */}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Items
