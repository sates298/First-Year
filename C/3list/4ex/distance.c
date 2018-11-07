#include "agents.h"
#include <math.h>

double distance(struct agent a1, struct agent a2)
{
  double distance,x,y;
  x=a1.x-a2.x;
  y=a1.y-a2.y;
  distance= sqrt(x*x + y*y);
  return distance;
}
