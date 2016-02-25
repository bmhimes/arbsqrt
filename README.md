# arbsqrt

This command-line application estimates the root of a square.

## Usage

The application accepts two parameters: the square and the desired precision level, as a percentage error in the squared estimate.  See the example below for estimating the root of 81 to within 0.1% squared error:

    lein run -s 81 -p 0.001

The final root estimate will be 9.000009415515176, which is 81.00016947936183 when squared.  This is 0.024% off after 5 iterations.

Of course, using the maximum level of precision leads to an extremely precise estimate.

    lein run -s 81 -p 10e-13

This time, the root estimate is 9.0, an exact answer.

## Why did you make this?

This project is intended as a small Clojure skills demonstration.

## License

Copyright © 2016 Bryan Himes.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
