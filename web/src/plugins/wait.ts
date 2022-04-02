const wait = (msec: number) => {
  return new Promise((resolve: (value: unknown) => void) => {
    setTimeout(resolve, msec)
  })
}

export default wait
